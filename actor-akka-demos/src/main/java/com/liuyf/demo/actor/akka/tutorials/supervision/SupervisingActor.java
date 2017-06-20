package com.liuyf.demo.actor.akka.tutorials.supervision;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class SupervisingActor extends AbstractActor {

    ActorRef child = getContext().actorOf(Props.create(SupervisedActor.class), "supervised-actor");
    
    @Override
    public Receive createReceive() {
        
        return receiveBuilder()
                .matchEquals("failChild", f -> {
                    child.tell("fail", getSelf());
                })
                .build();
    }
    
    public static void main(String[] args) throws InterruptedException {
        
        ActorSystem system = ActorSystem.create();
        
        ActorRef supervisingActor = system.actorOf(Props.create(SupervisingActor.class), "supervising-actor");
        supervisingActor.tell("failChild", ActorRef.noSender());
        
        Thread.sleep(20000);
        
        supervisingActor.tell("failChild", ActorRef.noSender());
    }

}
