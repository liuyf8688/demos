package com.liuyf.demo.actor.akka.tutorials.actor.lifecycle;

import akka.actor.AbstractActor;

public class StartStopActor2 extends AbstractActor {
    
    @Override
    public void preStart() throws Exception {
        
        System.out.println("second started");
    }

    @Override
    public void postStop() throws Exception {

        System.out.println("second stopped");
    }
    
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .build();
    }


}
