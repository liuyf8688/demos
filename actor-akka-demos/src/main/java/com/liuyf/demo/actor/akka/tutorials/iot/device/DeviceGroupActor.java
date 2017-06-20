package com.liuyf.demo.actor.akka.tutorials.iot.device;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.duration.FiniteDuration;

public class DeviceGroupActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    
    final String groupId;
    
    public DeviceGroupActor(String groupId) {
        
        this.groupId = groupId;
    }
    
    public static Props props(String groupId) {
        
        return Props.create(DeviceGroupActor.class, groupId);
    }
    
    public static final class RequestAllTemperatures {
        
        final long requestId;
        
        public RequestAllTemperatures(long requestId) {
            this.requestId = requestId;
        }
    }
    
    public static final class RespondAllTemperatures {
        
        final long requestId;
        final Map<String, TemperatureReading> temperatures;
        
        public RespondAllTemperatures(long requestId, Map<String, TemperatureReading> temperatures) {
            this.requestId = requestId;
            this.temperatures = temperatures;
        }
    }
    
    public static interface TemperatureReading {
        
    }
    
    public static final class Temperature implements TemperatureReading {
        
        public final double value;
        
        public Temperature(double value) {
            this.value = value;
        }
    }
    
    public static final class TemperatureNotAvailable implements TemperatureReading {
        
    }
    
    public static final class DeviceNotAvailable implements TemperatureReading {
        
    }
    
    public static final class DeviceTimeOut implements TemperatureReading {
        
    }
    
    public static final class RequestDeviceList {
        final long requestId;

        public RequestDeviceList(long requestId) {
            this.requestId = requestId;
        }
    }
    
    public static final class ReplyDeviceList {
        final long requestId;
        final Set<String> ids;

        public ReplyDeviceList(long requestId, Set<String> ids) {
            this.requestId = requestId;
            this.ids = ids;
        }
    }
    
    final Map<String, ActorRef> deviceIdToActor = new HashMap<>();
    final Map<ActorRef, String> actorToDeviceId = new HashMap<>();
    
    @Override
    public void preStart() {
        
        log.info("DeviceGroup {} started", groupId);
    }
    
    @Override
    public void postStop() {
        
        log.info("DeviceGroup {} stopped", groupId);
    }
    
    private void onTrackDevice(DeviceManagerActor.RequestTrackDevice trackDevice) {
        
        if (this.groupId.equals(trackDevice.groupId)) {
            
            ActorRef deviceActor = deviceIdToActor.get(trackDevice.deviceId);
            if(deviceActor != null) {
                
                deviceActor.forward(trackDevice, getContext());
            } else {
                
                log.info("Creating device actor for {}", trackDevice.deviceId);
                deviceActor = getContext().actorOf(DeviceActor.props(groupId, trackDevice.deviceId));
                actorToDeviceId.put(deviceActor, trackDevice.deviceId);
                deviceIdToActor.put(trackDevice.deviceId, deviceActor);
                deviceActor.forward(trackDevice, getContext());
            }
        } else {
            
            log.warning("Ignoring TrackDevice request for {}. This actor is responsible for {}",
                    trackDevice.groupId,
                    this.groupId);
            
        }
    }
    
    private void onDeviceList(RequestDeviceList r) {
        
        getSender().tell(new ReplyDeviceList(r.requestId,  deviceIdToActor.keySet()), getSelf());
    }
    
    private void onTerminated(Terminated t) {
        
        ActorRef deviceActor = t.getActor();
        String deviceId = actorToDeviceId.get(deviceActor);
        log.info("Device Actor for {} has been terminated", deviceId);
        actorToDeviceId.remove(deviceActor);
        deviceIdToActor.remove(deviceId);
    }
    
    private void onAllTemperatures(RequestAllTemperatures r) {
        
        getContext().actorOf(
                DeviceGroupQueryActor.props(
                        actorToDeviceId,
                        r.requestId,
                        getSender(),
                        new FiniteDuration(2, TimeUnit.SECONDS)));
    }
    
    @Override
    public Receive createReceive() {
        
        return receiveBuilder()
                .match(DeviceManagerActor.RequestTrackDevice.class, this :: onTrackDevice)
                .match(RequestDeviceList.class, this :: onDeviceList)
                .match(Terminated.class, this :: onTerminated)
                .match(RequestAllTemperatures.class, this :: onAllTemperatures)
                .build();
    }

}
