package com.liuyf.demos.java8.lambda.runnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionComparatorWithLambda {

	public static void main(String[] args) {
		
		List<Integer> lists = new ArrayList<>();
		lists.add(100);
		lists.add(9);
		lists.add(90);
		lists.add(200);
		
//		lists.sort((o1, o2) -> -1 * o1.compareTo(o2));
		// (1) single condition
		Collections.sort(lists, (o1, o2) -> o1.compareTo(o2));
		// Collections.sort(lists, (o1, o2) -> { return -1 * o1.compareTo(o2); });
		// (2) multiple conditions
		Collections.sort(lists, (o1, o2) -> { 
			if (o1 == 9) {
				return 0;
			} else {
				return -1;
			}
		});
		System.out.println("Original: ");
		lists.forEach((o1) -> System.out.println(o1));

		System.out.println("=====================================");
		
		// (3) basic lambda expression
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Yanfeng", "Liu", 32));
		persons.add(new Person("MingYue", "Liu", 31));
		persons.add(new Person("Shuai", "Liu", 28));
		persons.add(new Person("MingYang", "Liu", 18));
		
		Collections.sort(persons, (Person p1, Person p2) -> {
			return p1.getFirstName().compareTo(p2.getFirstName());
		});
		System.out.println("Basic lambda expression: ");
		persons.forEach((p) -> System.out.println(p.getFirstName()));
		
		System.out.println("=====================================");
		
		// (4) No type Definitions
		Collections.sort(persons, (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName()));
		System.out.println("No type definitions: ");
		persons.forEach((p) -> System.out.println(p.getFirstName()));
		
		System.out.println("=====================================");
		
		// (5) Sort using reference with static method
		Collections.sort(persons, CollectionComparatorWithLambda::compareByFirstName);
		System.out.println("Using static method reference: ");
		persons.forEach((p) -> System.out.println(p.getFirstName()));
		
		System.out.println("=====================================");
		
		// (6) Sort using to extract Comparator
		Collections.sort(persons, Comparator.comparing(Person::getAge));
		System.out.println("Extract Comparator: ");
		persons.forEach((p) -> System.out.println(p.getFirstName()));
		
		System.out.println("=====================================");
		
		// (7) Reverse Comparator
		Comparator<Person> comparator = (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName());
		Collections.sort(persons, comparator.reversed());
		System.out.println("Reverse Comparator: ");
		persons.forEach((p) -> System.out.println(p.getFirstName()));
		
		System.out.println("=====================================");
		
		// (8) Multiple conditions with Lambda
		Collections.sort(persons, Comparator.comparing(Person::getFirstName).thenComparing(Person::getAge));
		System.out.println("Multiple conditions with Lambda: ");
		persons.forEach((p) -> System.out.println(p.getFirstName()));
	}
	
	private static int compareByFirstName(Person p1, Person p2) {
		return -1 * p1.getFirstName().compareTo(p2.getFirstName());
	}
	

	private static class Person {
		
		private String firstName;
		private String lastName;
		private int age;
		
		public Person(String firstName, String lastName, int age) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.age = age;
		}
		
		public String getFirstName() {
			return firstName;
		}
		@SuppressWarnings("unused")
		public String getLastName() {
			return lastName;
		}
		public int getAge() {
			return age;
		}

	}
}
