package com.liuyf.demos.java8.official.tutorials;

public class Calculator {

	interface IntegerMath {
		
		int operation(int a, int b);
		
	}
	
	public int operateBinary(int a, int b, IntegerMath op) {
		return op.operation(a, b);
	}
	
	public static void main(String[] args) {
		
		Calculator calculator = new Calculator();
		IntegerMath addition = (a, b) -> a + b;
		System.out.println("40 + 2 = " + calculator.operateBinary(40, 2, addition));
		
		System.out.println();
		
		IntegerMath substraction = (a, b) -> a - b;
		System.out.println("40 - 2 = " + calculator.operateBinary(40, 2, substraction));
	}
}
