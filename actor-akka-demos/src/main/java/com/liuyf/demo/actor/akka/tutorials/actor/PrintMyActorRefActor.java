package com.liuyf.demo.actor.akka.tutorials.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class PrintMyActorRefActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("printit", p -> {
                    ActorRef secondRef = getContext().actorOf(Props.empty(), "second-actor");
                    System.out.println("Second: " + secondRef);
                }).build();
    }
    
    public static void main(String[] args) {
        
        ActorSystem actorSystem = ActorSystem.create();
        ActorRef firstRef = actorSystem.actorOf(Props.create(PrintMyActorRefActor.class), "first-actor");
        
        System.out.println("First: " + firstRef);
        firstRef.tell("printit", ActorRef.noSender());
        
        // FIXME 如何成功退出并关闭Actor
    }

}
