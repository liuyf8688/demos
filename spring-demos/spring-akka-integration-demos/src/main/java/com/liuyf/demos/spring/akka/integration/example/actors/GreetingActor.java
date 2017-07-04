package com.liuyf.demos.spring.akka.integration.example.actors;

import akka.actor.UntypedActor;
import com.liuyf.demos.spring.akka.integration.example.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by tony on 2017/7/3.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GreetingActor extends UntypedActor {

    private GreetingService greetingService;

    public GreetingActor() {
    }

    @Autowired
    public GreetingActor(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public void onReceive(Object message) throws Throwable {

        if (message instanceof Greet) {
            String name = ((Greet) message).getName();
            getSender().tell(greetingService.greet(name), getSelf());
        } else {
            unhandled(message);
        }
    }

    public static class Greet {

        private String name;

        public Greet() {
        }

        public Greet(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
