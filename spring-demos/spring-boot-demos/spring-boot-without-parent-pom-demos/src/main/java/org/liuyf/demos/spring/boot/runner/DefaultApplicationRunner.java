package org.liuyf.demos.spring.boot.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultApplicationRunner implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		System.out.println("++++++++++++ current ApplicationRunner ++++++++++++: " + this.getClass());
		
		for (String optionName : arg0.getOptionNames()) {
			System.out.println("++++++++++++ Option Name ++++++++++++: " + optionName);
		}
	}

}
