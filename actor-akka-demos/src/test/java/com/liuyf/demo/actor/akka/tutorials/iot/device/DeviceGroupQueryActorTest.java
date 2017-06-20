package com.liuyf.demo.actor.akka.tutorials.iot.device;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import scala.concurrent.duration.FiniteDuration;

public class DeviceGroupQueryActorTest {

    private ActorSystem system;
    
    @Before
    public void setup() {
        
        system = ActorSystem.create();
    }
    
    @After
    public void destory() {
        
        system.terminate();
    }
    
    @Test
    public void testReturnTemperatureValueForWorkingDevices() {
        
        TestKit requester = new TestKit(system);
        
        TestKit device1 = new TestKit(system);
        TestKit device2 = new TestKit(system);
        
        Map<ActorRef, String> actorToDeviceId = new HashMap<>();
        actorToDeviceId.put(device1.getRef(), "device1");
        actorToDeviceId.put(device2.getRef(), "device2");
        
        ActorRef queryActor = system.actorOf(DeviceGroupQueryActor.props(actorToDeviceId,
                1L,
                requester.getRef(),
                new FiniteDuration(3, TimeUnit.SECONDS)));
        
        assertEquals(0L, device1.expectMsgClass(DeviceActor.ReadTemperature.class).requestId);
        assertEquals(0L, device2.expectMsgClass(DeviceActor.ReadTemperature.class).requestId);
        
        queryActor.tell(new DeviceActor.RespondTemperature(0L, Optional.of(1.0)), device1.getRef());
        queryActor.tell(new DeviceActor.RespondTemperature(0L, Optional.of(2.0)), device2.getRef());
        
        
        DeviceGroupActor.RespondAllTemperatures response = requester.expectMsgClass(DeviceGroupActor.RespondAllTemperatures.class);
        assertEquals(1L, response.requestId);
        
        Map<String, DeviceGroupActor.TemperatureReading> expectedTemperatures = new HashMap<>();
        expectedTemperatures.put("device1", new DeviceGroupActor.Temperature(1.0));
        expectedTemperatures.put("device2", new DeviceGroupActor.Temperature(2.0));
        
        assertEqualsTemperatures(expectedTemperatures, response.temperatures);
    }

//    private void assertEqualsTemperatures(Map<String, TemperatureReading> expectedTemperatures,
//            Map<String, TemperatureReading> temperatures) {
//        
//        assertEquals(expectedTemperatures.size(), temperatures.size());
//        
//        for (Entry<String, DeviceGroupActor.TemperatureReading> entry : expectedTemperatures.entrySet()) {
//            TemperatureReading reading = temperatures.get(entry.getKey());
//            if (reading instanceof DeviceGroupActor.Temperature) {
//                DeviceGroupActor.Temperature expected = (DeviceGroupActor.Temperature)(entry.getValue());
//                DeviceGroupActor.Temperature actual = (DeviceGroupActor.Temperature)(temperatures.get(entry.getKey()));
//                assertEquals(new Double(expected.value), new Double(actual.value));
//            } else if (reading instanceof DeviceGroupActor.TemperatureNotAvailable) {
//                
//                DeviceGroupActor.TemperatureNotAvailable expected = (DeviceGroupActor.TemperatureNotAvailable)(entry.getValue());
//                DeviceGroupActor.TemperatureNotAvailable actual = (DeviceGroupActor.TemperatureNotAvailable)(temperatures.get(entry.getKey()));
//                
//                assertTrue(expected == actual);
//            }
//        }
//    }
    
    public static void assertEqualsTemperatures(Map<String, DeviceGroupActor.TemperatureReading> expected, Map<String, DeviceGroupActor.TemperatureReading> actual) {
        for (Map.Entry<String, DeviceGroupActor.TemperatureReading> entry : expected.entrySet()) {
            DeviceGroupActor.TemperatureReading expectedReading = entry.getValue();
            DeviceGroupActor.TemperatureReading actualReading = actual.get(entry.getKey());
            assertNotNull(actualReading);
            assertEquals(expectedReading.getClass(), actualReading.getClass());
            if (expectedReading instanceof DeviceGroupActor.Temperature) {
                assertEquals(((DeviceGroupActor.Temperature) expectedReading).value, ((DeviceGroupActor.Temperature) actualReading).value, 0.01);
            }
        }

        assertEquals(expected.size(), actual.size());
    }
}
