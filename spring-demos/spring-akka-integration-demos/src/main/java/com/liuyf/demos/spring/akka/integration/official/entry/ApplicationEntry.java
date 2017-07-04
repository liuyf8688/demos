package com.liuyf.demos.spring.akka.integration.official.entry;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.liuyf.demos.spring.akka.integration.official.actor.CountingActor;
import com.liuyf.demos.spring.akka.integration.official.extesion.SpringExtension;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

/**
 * Created by tony on 2017/7/4.
 */
public class ApplicationEntry {

    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("com.liuyf.demos.spring.akka.integration.official");
        ctx.refresh();

        ActorSystem system = ctx.getBean(ActorSystem.class);
        ActorRef counter = system.actorOf(
                SpringExtension.springExtProvider.get(system).props("countingActor"),
                "counter"
        );

        counter.tell(new CountingActor.Count(), null);
        counter.tell(new CountingActor.Count(), null);
        counter.tell(new CountingActor.Count(), null);

        FiniteDuration duration = FiniteDuration.create(3, TimeUnit.SECONDS);
        Future<Object> result = Patterns.ask(counter, new CountingActor.Get(), Timeout.durationToTimeout(duration));

        try {

            System.out.println("Got back: " + Await.result(result, duration));
        } catch (Exception e) {

            System.err.println("Failed getting result: " + e.getMessage());
            throw e;
        } finally {

            system.terminate();
        }
    }
}
