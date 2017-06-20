package com.liuyf.demo.actor.akka.tutorials.iot.device;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;

public class DeviceActorTest {

    @Test
    public void test() {
        
        ActorSystem system = ActorSystem.create();
        
        TestKit probe = new TestKit(system);
        
        ActorRef deviceActor = system.actorOf(DeviceActor.props("group", "device"));
        deviceActor.tell(new DeviceActor.ReadTemperature(42L), probe.getRef());
        
        DeviceActor.RespondTemperature respondTemperature = probe.expectMsgClass(DeviceActor.RespondTemperature.class);
        
        
        assertEquals(42L, respondTemperature.requestId);
        assertEquals(Optional.empty(), respondTemperature.value);
    }
    
    @Test
    public void testReplyWithLatestTemperatureReading() {
        
        ActorSystem system = ActorSystem.create();
        
        TestKit probe = new TestKit(system);
        
        ActorRef deviceActor = system.actorOf(DeviceActor.props("group", "device"));
        
        deviceActor.tell(new DeviceActor.RecordTemperature(1L, 24.0), probe.getRef());
        assertEquals(1L, probe.expectMsgClass(DeviceActor.TemperatureRecorded.class).requestId);
        
        deviceActor.tell(new DeviceActor.ReadTemperature(2L), probe.getRef());
        DeviceActor.RespondTemperature respond1 = probe.expectMsgClass(DeviceActor.RespondTemperature.class);
        assertEquals(2L, respond1.requestId);
        assertEquals(Optional.of(24.0), respond1.value);
        
        deviceActor.tell(new DeviceActor.RecordTemperature(3L, 55.0), probe.getRef());
        assertEquals(3L, probe.expectMsgClass(DeviceActor.TemperatureRecorded.class).requestId);
        
        deviceActor.tell(new DeviceActor.ReadTemperature(4L), probe.getRef());
        DeviceActor.RespondTemperature respond2 = probe.expectMsgClass(DeviceActor.RespondTemperature.class);
        assertEquals(4L, respond2.requestId);
        assertEquals(Optional.of(55.0), respond2.value);
    }
    
    @Test
    public void testReplyToRegistrationRequests() {
        
        ActorSystem system = ActorSystem.create();
        
        TestKit probe = new TestKit(system);
        
        ActorRef deviceActor = system.actorOf(DeviceActor.props("group", "device"));
        
        deviceActor.tell(new DeviceManagerActor.RequestTrackDevice("group", "device"), probe.getRef());
        probe.expectMsgClass(DeviceManagerActor.DeviceRegistered.class);
        assertEquals(deviceActor, probe.getLastSender());
    }
    
    @Test
    public void testIgnoreWrongRegistrationRequests() {
        
        ActorSystem system = ActorSystem.create();
        
        TestKit probe = new TestKit(system);
        
        ActorRef deviceActor = system.actorOf(DeviceActor.props("group", "device"));
        
        deviceActor.tell(new DeviceManagerActor.RequestTrackDevice("wrongGroup", "device"), probe.getRef());
        probe.expectNoMsg();
        
        deviceActor.tell(new DeviceManagerActor.RequestTrackDevice("group", "wrongDevice"), probe.getRef());
        probe.expectNoMsg();
    }

}
