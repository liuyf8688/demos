package com.liuyf.demos.spring.akka.integration.official.actor;

import akka.actor.UntypedAbstractActor;
import akka.actor.UntypedActor;
import com.liuyf.demos.spring.akka.integration.official.service.CountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by tony on 2017/7/4.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CountingActor extends UntypedAbstractActor {

    public static class Count {}

    public static class Get {}

    final CountingService countingService;

    private int count = 0;

    @Autowired
    public CountingActor(CountingService countingService) {
        this.countingService = countingService;
    }

    @Override
    public void onReceive(Object message) throws Throwable {

        if (message instanceof Count) {
            count = countingService.increment(count);
        } else if (message instanceof Get) {
            getSender().tell(count, getSelf());
        } else {
            unhandled(message);
        }
    }
}
