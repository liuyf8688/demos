package com.liuyf.demos.java8.official.tutorials;

import java.util.Arrays;
import java.util.function.Supplier;

public class MethodReferenceDemo {

	public static void main(String[] args) throws Exception {
		
		// 1. Reference to an Instance Method of an Arbitrary Object of a Particular Type
		String[] stringArray = { "James", "Barbara", "Mary" };
		Arrays.sort(stringArray, String::compareTo);
		System.out.println(Arrays.toString(stringArray));
		
		System.out.println();
		
		// 2. Reference to a Constructor
		StringBuffer rt = receiveAnConstructorMethod(stringArray, StringBuffer::new);
		System.out.println(rt);
	}
	
	static StringBuffer receiveAnConstructorMethod(String[] data, Supplier<StringBuffer> t) throws Exception {
		
		// it will return a new value at any moment call the t.get(); 
		StringBuffer sb = t.get();
		
//		System.out.println(t.get().hashCode());
//		System.out.println(sb.hashCode());
		
		for (String item : data) {
//			System.out.println(t.get().hashCode());
//			System.out.println("=====");
			sb.append("|").append(item);
		}
//		System.out.println(t.get().hashCode());
//		System.out.println(t.get());
		return sb;
	}
	
}
