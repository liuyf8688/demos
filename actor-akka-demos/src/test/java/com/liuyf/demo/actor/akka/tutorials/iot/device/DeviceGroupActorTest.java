package com.liuyf.demo.actor.akka.tutorials.iot.device;

import static org.junit.Assert.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.testkit.javadsl.TestKit;

public class DeviceGroupActorTest {

    
    @Test
    public void testListActiveDevices() {
        
        ActorSystem system = ActorSystem.create();
        
        TestKit probe = new TestKit(system);
        
        ActorRef groupActor = system.actorOf(DeviceGroupActor.props("group"));
        
        groupActor.tell(new DeviceManagerActor.RequestTrackDevice("group", "device1"), probe.getRef());
        probe.expectMsgClass(DeviceManagerActor.DeviceRegistered.class);
        
        groupActor.tell(new DeviceManagerActor.RequestTrackDevice("group", "device2"), probe.getRef());
        probe.expectMsgClass(DeviceManagerActor.DeviceRegistered.class);
        
        groupActor.tell(new DeviceGroupActor.RequestDeviceList(0L), probe.getRef());
        DeviceGroupActor.ReplyDeviceList reply = probe.expectMsgClass(DeviceGroupActor.ReplyDeviceList.class);
        assertEquals(0L, reply.requestId);
        assertEquals(Stream.of("device1", "device2").collect(Collectors.toSet()), reply.ids);
    }
    
    @Test
    public void testListActiveDevicesAfterOneShutDown() {
        
        ActorSystem system = ActorSystem.create();
        
        TestKit probe = new TestKit(system);
        
        ActorRef groupActor = system.actorOf(DeviceGroupActor.props("group"));
        
        groupActor.tell(new DeviceManagerActor.RequestTrackDevice("group", "device1"), probe.getRef());
        probe.expectMsgClass(DeviceManagerActor.DeviceRegistered.class);
        ActorRef toShutdown = probe.getLastSender();
        
        groupActor.tell(new DeviceManagerActor.RequestTrackDevice("group", "device2"), probe.getRef());
        probe.expectMsgClass(DeviceManagerActor.DeviceRegistered.class);
        
        groupActor.tell(new DeviceGroupActor.RequestDeviceList(0L), probe.getRef());
        DeviceGroupActor.ReplyDeviceList reply = probe.expectMsgClass(DeviceGroupActor.ReplyDeviceList.class);
        assertEquals(0L, reply.requestId);
        assertEquals(Stream.of("device1", "device2").collect(Collectors.toSet()), reply.ids);
        
        probe.watch(toShutdown);
        //
        toShutdown.tell(PoisonPill.getInstance(), probe.getLastSender());
        probe.expectTerminated(toShutdown);
        
//         using awaitAssert to retry because it might take longer for the groupActor
//         to see the Terminated, that order is undefined
        probe.awaitAssert(() -> {
            
            groupActor.tell(new DeviceGroupActor.RequestDeviceList(1L), probe.getRef());
            DeviceGroupActor.ReplyDeviceList r =
                    probe.expectMsgClass(DeviceGroupActor.ReplyDeviceList.class);
            
            assertEquals(1L, r.requestId);
            assertEquals(Stream.of("device2").collect(Collectors.toSet()), r.ids);
            return null;
        });
    }
    
}
