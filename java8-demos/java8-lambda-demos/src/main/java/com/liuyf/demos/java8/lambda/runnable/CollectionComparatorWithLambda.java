package com.liuyf.demos.java8.lambda.runnable;

import java.util.ArrayList;
import java.util.Collections;
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
		persons.forEach((p) -> System.out.println(p.getFirstName()));
		
		System.out.println("=====================================");
		
		// (4) No type Definitions
		Collections.sort(persons, (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName()));
		persons.forEach((p) -> System.out.println(p.getFirstName()));
		
		// (5) Sort using reference with static method
	}
	
	private static 
	static class Person {
		
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
		public String getLastName() {
			return lastName;
		}
		public int getAge() {
			return age;
		}

	}
}
