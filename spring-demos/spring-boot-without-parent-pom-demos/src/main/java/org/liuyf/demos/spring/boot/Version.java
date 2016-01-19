package org.liuyf.demos.spring.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Version {

	@Value("${project.version}")
	private String version;

	public String getVersion() {
		return version;
	}
	
}
