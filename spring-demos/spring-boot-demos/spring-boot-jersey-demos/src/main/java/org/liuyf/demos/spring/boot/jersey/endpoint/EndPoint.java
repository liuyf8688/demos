package org.liuyf.demos.spring.boot.jersey.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Component
@Path("/hello")
public class EndPoint {

	@GET
	public String message() {
		return "Hello";
	}
}
