package example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.liuyf.demos.spring.akka.integration.example.actors.GreetingActor;
import com.liuyf.demos.spring.akka.integration.example.config.GlobalConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

import static com.liuyf.demos.spring.akka.integration.example.SpringExtension.SPRING_EXTENSION_PROVIDER;

/**
 * Created by tony on 2017/7/3.
 */
@ContextConfiguration(classes = GlobalConfig.class)
public class SpringAkkaIntegrationTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ActorSystem system;

    @Test
    public void whenCallingGreetingActor_thenActorGreetsTheCaller() throws Exception {

        ActorRef greeter = system.actorOf(
                SPRING_EXTENSION_PROVIDER.get(system).props("greetingActor"), "greeter");

        FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
        Timeout timeout = Timeout.durationToTimeout(duration);

        Future<Object> result = Patterns.ask(greeter, new GreetingActor.Greet("John"), timeout);

        Assert.assertEquals("Hello, John", Await.result(result, duration));
    }

    @After
    public void tearDown() {

        system.terminate();
    }
}
