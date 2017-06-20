package com.liuyf.demo.actor.akka.tutorials.iot.device;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.liuyf.demo.actor.akka.tutorials.iot.device.DeviceGroupActor.TemperatureReading;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.Props;
import akka.actor.Terminated;
import scala.concurrent.duration.FiniteDuration;

public class DeviceGroupQueryActor extends AbstractActor {

//    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    
    public static final class CollectionTimeout {
        
    }
    
    final Map<ActorRef, String> actorToDeviceId;
    final long requestId;
    final ActorRef requester;
    
    Cancellable queryTimeoutTimer;
    
    public DeviceGroupQueryActor(Map<ActorRef, String> actorToDeviceId,
            long requestId,
            ActorRef requester,
            FiniteDuration timeout) {
        this.actorToDeviceId = actorToDeviceId;
        this.requestId = requestId;
        this.requester = requester;
        
        queryTimeoutTimer = getContext()
                .getSystem()
                .scheduler()
                .scheduleOnce(timeout, getSelf(), new CollectionTimeout(), getContext().dispatcher(), getSelf());
    }
    
    public static Props props(Map<ActorRef, String> actorToDeviceId,
            long requestId,
            ActorRef requester,
            FiniteDuration timeout) {
        return Props.create(DeviceGroupQueryActor.class,
                actorToDeviceId,
                requestId,
                requester,
                timeout);
    }
    
    @Override
    public void preStart() {
        
        for (ActorRef deviceActor : actorToDeviceId.keySet()) {
            getContext().watch(deviceActor);
            deviceActor.tell(new DeviceActor.ReadTemperature(0L), getSelf());
        }
    }
    
    @Override
    public void postStop() {
        queryTimeoutTimer.cancel();
    }
    
    @Override
    public Receive createReceive() {
        return waitingForReplies(new HashMap<>(), actorToDeviceId.keySet());
    }

    private Receive waitingForReplies(Map<String, DeviceGroupActor.TemperatureReading> repliesSoFar,
            Set<ActorRef> stillWating) {
        
        return receiveBuilder()
                .match(DeviceActor.RespondTemperature.class, r -> {
                    
                    ActorRef deviceActor = getSender();
                    DeviceGroupActor.TemperatureReading reading =
                            r.value.map(v -> (DeviceGroupActor.TemperatureReading) new DeviceGroupActor.Temperature(v))
                            .orElse(new DeviceGroupActor.TemperatureNotAvailable());
                    
                    receivedResponse(deviceActor, reading, stillWating, repliesSoFar);
                })
                .match(Terminated.class, t -> {
                    receivedResponse(t.getActor(), new DeviceGroupActor.DeviceNotAvailable(), stillWating, repliesSoFar);
                })
                .match(CollectionTimeout.class, t -> {
                    
                    Map<String, DeviceGroupActor.TemperatureReading> replies = new HashMap<>(repliesSoFar);
                    for (ActorRef deviceActor : stillWating) {
                        
                        String deviceId = actorToDeviceId.get(deviceActor);
                        replies.put(deviceId, new DeviceGroupActor.DeviceTimeOut());
                    }
                    
                    requester.tell(new DeviceGroupActor.RespondAllTemperatures(requestId, replies),
                            getSelf());
                    getContext().stop(getSelf());
                })
                .build();
    }

    private void receivedResponse(ActorRef deviceActor,
            TemperatureReading reading,
            Set<ActorRef> stillWating,
            Map<String, TemperatureReading> repliesSoFar) {
        
        getContext().unwatch(deviceActor);
        String deviceId = actorToDeviceId.get(deviceActor);
        
        Set<ActorRef> newStillWaiting = new HashSet<>(stillWating);
        newStillWaiting.remove(deviceActor);
        
        Map<String, DeviceGroupActor.TemperatureReading> newRepliesSoFar = new HashMap<>(repliesSoFar);
        newRepliesSoFar.put(deviceId, reading);
        
        if (newStillWaiting.isEmpty()) {
            
            requester.tell(new DeviceGroupActor.RespondAllTemperatures(requestId, newRepliesSoFar), getSelf());
            getContext().stop(getSelf());
        } else {
            
            getContext().become(waitingForReplies(newRepliesSoFar, newStillWaiting));
        }

        System.out.println("===>>>: " + deviceId);
        System.out.println();
        System.out.println("==================================================");
        System.out.println();
        System.out.println(newRepliesSoFar);
        System.out.println();
        System.out.println("==================================================");
        System.out.println();
        System.out.println(newStillWaiting);
    }

}
