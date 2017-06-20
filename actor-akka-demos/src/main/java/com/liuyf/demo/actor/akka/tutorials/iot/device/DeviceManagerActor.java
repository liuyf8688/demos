package com.liuyf.demo.actor.akka.tutorials.iot.device;

import java.util.HashMap;
import java.util.Map;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class DeviceManagerActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    
    public static Props props() {
        
        return Props.create(DeviceManagerActor.class);
    }
    
    public static final class RequestTrackDevice {
        
        public final String groupId;
        
        public final String deviceId;
        
        public RequestTrackDevice(String groupId, String deviceId) {
            
            this.groupId = groupId;
            this.deviceId = deviceId;
        }
    }
    
    public static final class DeviceRegistered {
        
    }
    
    final Map<String, ActorRef> groupIdToActor = new HashMap<>();
    final Map<ActorRef, String> actorToGroupId = new HashMap<>();
    
    @Override
    public void preStart() {
        log.info("DeviceManager started");
    }
    
    @Override
    public void postStop() {
        log.info("DeviceManager stopped");
    }
    
    private void onTrackDevice(RequestTrackDevice trackDevice) {
        
        String groupId = trackDevice.groupId;
        ActorRef ref = groupIdToActor.get(groupId);
        if (ref != null) {
            ref.forward(trackDevice, getContext());
        } else {
            
            log.info("Creating device group actor for {}", groupId);
            ActorRef groupActor = getContext().actorOf(DeviceGroupActor.props(groupId), "group-" + groupId);
            getContext().watch(groupActor);
            groupActor.forward(trackDevice, getContext());
            groupIdToActor.put(groupId, groupActor);
            actorToGroupId.put(groupActor, groupId);
        }
    }
    
    private void onTerminated(Terminated t) {
        
        ActorRef groupActor = t.getActor();
        String groupId = actorToGroupId.get(groupActor);
        log.info("Device group actor for {} has been terminated", groupId);
        actorToGroupId.remove(groupActor);
        groupIdToActor.remove(groupId);
    }
    
    @Override
    public Receive createReceive() {
        
        return receiveBuilder()
                .match(RequestTrackDevice.class, this :: onTrackDevice)
                .match(Terminated.class, this :: onTerminated)
                .build();
    }

}
