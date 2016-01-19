package org.liuyf.demos.spring.boot.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultCommandLineRunner implements CommandLineRunner {

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("++++++++++++ current CommandLineRunner ++++++++++++: " + this.getClass());
	}

}
