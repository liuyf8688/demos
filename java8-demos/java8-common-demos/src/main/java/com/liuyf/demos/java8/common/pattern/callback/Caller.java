package com.liuyf.demos.java8.common.pattern.callback;

public class Caller {

	public void register(Callback callback) {
		callback.methodToCallback();
	}
	
	public static void main(String[] args) {
		Caller caller = new Caller();
		Callback callback = new CallbackImpl();
		caller.register(callback);
	}

}
