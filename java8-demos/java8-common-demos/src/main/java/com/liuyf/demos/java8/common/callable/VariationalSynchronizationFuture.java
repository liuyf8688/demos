package com.liuyf.demos.java8.common.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class VariationalSynchronizationFuture {

	private static ExecutorService executor = Executors.newFixedThreadPool(2);
	
	public static void main(String[] args) throws Exception {
		
		try {
			new VariationalSynchronizationFuture().test();
		} finally {
			if (!executor.isShutdown()) {
				executor.shutdown();
			}
		}
	}
	
	public void test() throws Exception {
		
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 2; i ++) {
			Integer rt = getResult(i);
			System.out.println(i + " ===>>> " + rt);
		}
		System.out.println(" ===>>> finish time: " + (System.currentTimeMillis() - startTime));
	}

	private Integer getResult(final int index) throws Exception {
		
		Callable<Integer> callable = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				
				Thread.sleep(3000);
				
				System.out.println(" ===>>> index: " + index);
				if (index == 0) {
					return null;
				} else {
					return 1;
				}
			}
		};
		
		Future<Integer> f = executor.submit(callable);
		
		try {
			return f.get(60, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			throw e;
		}
	}
	
}
