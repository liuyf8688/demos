package com.liuyf.demos.java8.lambda.runnable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FunctionPredicateWithLambda {

	public static void main(String[] args) {
		
		List<String> languages = Arrays.asList("Java", "Scala", "C", "C++", "Haskell", "Lisp");
		
		System.out.println("Using startWith method: ");
		filter(languages, (str) -> str.startsWith("C"));
		
	}

	private static void filter(List<String> languages, Predicate<String> condition) {
		languages.stream().filter((language) -> (condition.test(language))).forEach((name) -> System.out.println(name));
	}
}
