package com.liuyf.demo.actor.akka.tutorials.actor.lifecycle;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class StartStopActor1 extends AbstractActor {
    
    @Override
    public void preStart() throws Exception {
        
        System.out.println("first started");
        getContext().actorOf(Props.create(StartStopActor2.class), "second");
    }

    @Override
    public void postStop() throws Exception {
        
        System.out.println("first stopped");
    }
    
    @Override
    public Receive createReceive() {
        
        return receiveBuilder()
                .matchEquals("stop", s -> {
                    getContext().stop(getSelf());
                })
                .build();
    }

    public static void main(String[] args) {
        
        ActorSystem actorSystem = ActorSystem.create();
        ActorRef first = actorSystem.actorOf(Props.create(StartStopActor1.class), "first");
        first.tell("stop", ActorRef.noSender());
    }

}
