package com.liuyf.demos.java8.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AsynchronizationFuture {
private static ExecutorService executor = Executors.newFixedThreadPool(2);
	
	public static void main(String[] args) throws Exception {
		
		try {
			new AsynchronizationFuture().test();
		} finally {
			if (!executor.isShutdown()) {
				executor.shutdown();
			}
		}
	}
	
	public void test() throws Exception {
		
		long startTime = System.currentTimeMillis();
		List<Future<Integer>> futures = new ArrayList<>();
		for (int i = 0; i < 2; i ++) {
			futures.add(getResult(i));
		}
		
		for (Future<Integer> f : futures) {
			try {
				System.out.println(" ===>>> " + f.get(60, TimeUnit.SECONDS));
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				throw e;
			}
		}
		
		System.out.println(" ===>>> finish time: " + (System.currentTimeMillis() - startTime));
	}

	private Future<Integer> getResult(final int index) throws Exception {
		
		Callable<Integer> callable = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				
				System.out.println(" ===>>> index: " + index);
				Thread.sleep(3000);
				
				if (index == 0) {
					return null;
				} else {
					return 1;
				}
			}
		};
		
		return executor.submit(callable);
	}
}
