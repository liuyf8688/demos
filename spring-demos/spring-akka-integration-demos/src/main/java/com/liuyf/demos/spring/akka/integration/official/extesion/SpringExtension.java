package com.liuyf.demos.spring.akka.integration.official.extesion;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import akka.actor.Props;
import org.springframework.context.ApplicationContext;

/**
 * Created by tony on 2017/7/4.
 */
public class SpringExtension extends AbstractExtensionId<SpringExtension.SpringExt> {

    public static SpringExtension springExtProvider = new SpringExtension();

    @Override
    public SpringExt createExtension(ExtendedActorSystem extendedActorSystem) {
        return new SpringExt();
    }

    public static class SpringExt implements Extension {

        private volatile ApplicationContext applicationContext;

        public void initialize(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        public Props props(String actorBeanName) {

            return Props.create(SpringActorProducer.class, applicationContext, actorBeanName);
        }
    }
}
