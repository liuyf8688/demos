package com.liuyf.demos.java8.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService es = Executors.newScheduledThreadPool(2);
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(es);
		
		List<Future<Integer>> futures = new ArrayList<>();
		
		for (int i = 0; i < 10; i ++) {
			
			final int index = i;
			Future<Integer> future = completionService.submit(new Callable<Integer>() {
				
				@Override
				public Integer call() throws Exception {
					if (index == 0) {
						Thread.sleep(10 * 10_000L);
					} else {
						Thread.sleep(10_000L);
					}
					return index;
				}
			});
			futures.add(future);
		}

		long start = System.currentTimeMillis();
		/**
		for (Future<Integer> future : futures) {
			System.out.println(future.get());
		}
		System.out.println(System.currentTimeMillis() - start);
		 */

		// 没有对比就没有伤害
		for (int i = 0; i < futures.size(); i ++) {
			Future<Integer> future = completionService.take();
			System.out.println(future.get());
		}

		System.out.println(System.currentTimeMillis() - start);

		es.shutdown();
	}
}
