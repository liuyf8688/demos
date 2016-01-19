package com.liuyf.demos.java8.official.tutorials;

import java.util.function.Consumer;

public class LambdaScopeTest {

	public int x = 0;
	
	class FirstLevel {
		
		public int x = 1;
		
		void methodInFirstLevel(int x) {
			
			Consumer<Integer> myConsumer = (y) ->
			{
				System.out.println("x = " + x);
				System.out.println("y = " + y);
				System.out.println("this.x = " + this.x);
				System.out.println("LambdaScopeTest.this.x = " + LambdaScopeTest.this.x);
			};
			
			System.out.println("==================");
			myConsumer.accept(25);
		}
		
	}
	
	public static void main(String[] args) {
		
		LambdaScopeTest st = new LambdaScopeTest();
		LambdaScopeTest.FirstLevel fl = st.new FirstLevel();
		fl.methodInFirstLevel(23);
	}
}
