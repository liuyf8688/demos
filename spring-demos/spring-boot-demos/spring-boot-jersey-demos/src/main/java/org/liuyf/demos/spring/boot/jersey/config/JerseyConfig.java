package org.liuyf.demos.spring.boot.jersey.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.liuyf.demos.spring.boot.jersey.endpoint.EndPoint;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(EndPoint.class);
	}
}
