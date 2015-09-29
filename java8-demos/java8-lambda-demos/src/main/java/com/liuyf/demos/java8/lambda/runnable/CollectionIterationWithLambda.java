package com.liuyf.demos.java8.lambda.runnable;

import java.util.Arrays;
import java.util.List;

public class CollectionIterationWithLambda {

	public static void main(String[] args) {

		// prior to Java 8
		List<String> features = Arrays.asList("Lambda", "Default Method", "Stream API", "Date and Time API");
		for (String feature : features) {
			System.out.println(feature);
		}
		
		System.out.println("================================");
		
		// Java 8
		List<String> _features = Arrays.asList("Lambda", "Default Method", "Stream API", "Date and Time API");
		_features.forEach(feature -> System.out.println(feature));
		
		System.out.println("================================");
		
		// another way
		_features.forEach(System.out::println);
	}

}
