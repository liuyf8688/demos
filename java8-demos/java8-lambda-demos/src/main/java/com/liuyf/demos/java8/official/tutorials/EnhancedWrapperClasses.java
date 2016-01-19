package com.liuyf.demos.java8.official.tutorials;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnhancedWrapperClasses {

	public static void main(String[] args) {
		
		Integer[] intArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		List<Integer> list = new ArrayList<>(Arrays.asList(intArray));
		System.out.println(
				"Sum of integers: " + list.stream().reduce(Integer :: sum).get()
				);
		
	}
}
