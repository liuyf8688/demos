package org.liuyf.demos.spring.boot.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "my")
@Component
public class Config {

	private List<String> servers = new ArrayList<>();
	
	private String[] candidateServers;

	public List<String> getServers() {
		return servers;
	}

	public String[] getCandidateServers() {
		return candidateServers;
	}

	public void setCandidateServers(String[] candidateServers) {
		this.candidateServers = candidateServers;
	}
	
}
