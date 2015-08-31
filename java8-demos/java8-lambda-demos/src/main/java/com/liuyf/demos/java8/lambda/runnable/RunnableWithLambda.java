package com.liuyf.demos.java8.lambda.runnable;

public class RunnableWithLambda {

	/**
	 * 
	 * This example shows the new syntaxes below:
	 * (1) (params) -> expression
	 * (2) (params) -> statement
	 * (3) (params) -> { statements }
	 * 
	 */
	public static void main(String[] args) {
		// prior to Java 8
		new Thread(new Runnable() {
			public void run() {
				System.out.println("Before Java8, too much code for too little to do");
			}
		}).start();
		
		// Java 8
		new Thread(() -> System.out.println("In Java8, Lambda expression rocks")).start();
	}
}
