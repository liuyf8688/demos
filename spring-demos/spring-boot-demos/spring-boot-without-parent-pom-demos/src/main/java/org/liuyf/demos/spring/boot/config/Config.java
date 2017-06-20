package org.liuyf.demos.spring.boot.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "my")
public class Config {

	private List<String> servers = new ArrayList<>();
	
	private String[] candidateServers;
	
    @Value("${endpoints.hypermedia.enabled}")
    private boolean endpointsHypermediaEnabled;

	public List<String> getServers() {
		return servers;
	}

	public String[] getCandidateServers() {
		return candidateServers;
	}

	public void setCandidateServers(String[] candidateServers) {
		this.candidateServers = candidateServers;
	}

    public boolean isEndpointsHypermediaEnabled() {
        return endpointsHypermediaEnabled;
    }
	
}
