package com.liuyf.demos.algorithm.bloomfilter;

import orestes.bloomfilter.BloomFilter;
import orestes.bloomfilter.FilterBuilder;

public class RegularBloomFilter {

	public static void main(String[] args) {

		BloomFilter<String> bf = new FilterBuilder(1000, 0.1).buildBloomFilter();
		System.out.println("The numbers of hash methods: " + bf.getHashes());
		System.out.println("The size of bigSet inside BloomFilter: " + bf.getSize());
		//Add a few elements
		bf.add("Just");
		bf.add("a");
		bf.add("test.");
		
		//Test if they are contained
		System.out.println(bf.contains("Just")); //true
		System.out.println(bf.contains("a")); //true
		System.out.println(bf.contains("test.")); //true
		
		//Add 300 elements
		for (int i = 0; i < 300; i++) {
			String element = "Element " + i;
			bf.add(element);
		}
		
		//Test with a non-existing element
		System.out.println(bf.contains("WingDangDoodel")); //false
		
		//test for false positives
		for (int i = 300; i < 1000; i++) {
			String element = "Element " + i;
			if(bf.contains(element)) {
				System.out.println(element); //two elements: 440, 669
			}
		}
		
		//Compare with the expected amount
		System.out.println(bf.getFalsePositiveProbability(303) * 700); //1.74
	}

}
