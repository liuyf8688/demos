package com.liuyf.demos.java8.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by tony on 2017/10/22.
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {

        List<Integer> ints = Arrays.asList(0, 1, 2, 3, 4, 5, 6);

        ints.forEach(i ->
                CompletableFuture.supplyAsync(() -> {
                    // System.out.println(i);
                    if (i == 0) {
                        try {
                            Thread.sleep(10_000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    return i;
                }).thenApply(integer -> {
                    System.out.println(integer);
                    // System.out.println("===========================");
                    return integer;
                })
        );
    }
}
