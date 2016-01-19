package com.liuyf.demos.java8.official.tutorials;

import java.util.concurrent.Callable;

public class TargetTypeDemo {

	static void invoke(Runnable r) {
		r.run();
	}
	
	static <T> T invoke(Callable<T> c) throws Exception {
		return c.call();
	}
	
	public static void main(String[] args) throws Exception {
		
		// For method arguments, the Java compiler determines the target type with two
		// other language features: overload resolution and type argument inference
		
		invoke(() -> System.out.println("run only once"));
		
		String s = invoke(() -> "done");
		
		System.out.println(s);
	}
}
