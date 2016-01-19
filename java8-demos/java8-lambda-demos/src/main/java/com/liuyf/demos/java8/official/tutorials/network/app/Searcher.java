package com.liuyf.demos.java8.official.tutorials.network.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.liuyf.demos.java8.official.tutorials.network.app.Person.Sex;
import com.liuyf.demos.java8.official.tutorials.network.app.assistant.CheckPerson;
import com.liuyf.demos.java8.official.tutorials.network.app.assistant.DefaultCheckPerson;

public class Searcher {

	public static void main(String[] args) {
		List<Person> roster = new ArrayList<>();
		roster.add(new Person("ZhangShan", LocalDate.of(1984, 10, 28), Sex.FEMALE, "shan.Zhang@cc.com"));
		roster.add(new Person("LiSi", LocalDate.of(1985, 9, 11), Sex.MALE, "si.Li@cc.com"));
		roster.add(new Person("WangWu", LocalDate.of(1988, 10, 25), Sex.MALE, "wu.Wang@cc.com"));
		roster.add(new Person("ZhaoLiu", LocalDate.of(1997, 7, 1), Sex.FEMALE, "liu.Zhao@cc.com"));
		
		System.out.println("Appoach 1 ");
		approach1_printPersonsOlderThan(roster, LocalDate.of(1986, 1, 31));
		System.out.println();
		
		System.out.println("Appoach 2 ");
		approach2_printPersonsWithinBirthRange(roster, LocalDate.of(1986, 1, 31), LocalDate.of(1992, 1, 31));
		System.out.println();
		
		System.out.println("Appoach 3 ");
		printPersons(roster, new DefaultCheckPerson());
		System.out.println();
		
		System.out.println("Appoach 4 --- Anonymous Class ");
		printPersons(roster, new CheckPerson() {
			
			@Override
			public boolean test(Person p) {
				return p.getGender() == Sex.FEMALE;
			}
		});
		System.out.println();
		
		System.out.println("Appoach 5 --- Lambda Expression ");
		printPersons(roster,
				(Person p) -> p.getGender() == Sex.FEMALE);
		System.out.println();
		
		System.out.println("Appoach 6 --- Use Standard Functional Interfaces with Lambda Expressions ");
		printPersonsWithPredicate(roster,
				p -> p.getEmailAddress().contains("Zhang"));
		System.out.println();
		
		System.out.println("Appoach 7.1 --- Use Lambda Expressions Throughout Your Application ");
		processPersons(roster,
				p -> p.getEmailAddress().contains("Zhang"),
				p -> p.printPerson());
		System.out.println();
		
		System.out.println("Appoach 7.2 --- Use Function ");
		processPersonsWithFunction(roster,
				p -> p.getEmailAddress().contains("Zhang"),
				p -> p.getEmailAddress(),
				email -> System.out.println(email));
		System.out.println();
		
		System.out.println("Appoach 8 --- Use Generics More Extensively ");
		processElements(roster,
				p -> p.getEmailAddress().contains("Zhang"),
				p -> p.getName() + " --- " + p.getEmailAddress(),
				text -> System.out.println(text));
		System.out.println();
		
		System.out.println("Appoach 9 --- Use Aggregate Operations That Accept Lambda Expressions as Parameters ");
		roster
			.stream()
			.filter(p -> p.getEmailAddress().contains("liu"))
			.map(p -> p.getEmailAddress())
			.forEach(email -> System.out.println(email));
		System.out.println();
	}
	
	private static <X, Y> void processElements(Iterable<X> source,
			Predicate<X> tester,
			Function<X, Y> mapper,
			Consumer<Y> block) {
		for (X p : source) {
			if (tester.test(p)) {
				Y data = mapper.apply(p);
				block.accept(data);
			}
		}
	}

	private static void processPersonsWithFunction(List<Person> roster,
			Predicate<Person> tester,
			Function<Person, String> mapper,
			Consumer<String> block) {
		for (Person p : roster) {
			if (tester.test(p)) {
				String data = mapper.apply(p);
				block.accept(data);
			}
		}
	}

	private static void processPersons(List<Person> roster,
			Predicate<Person> tester,
			Consumer<Person> block) {
		for (Person p : roster) {
			if (tester.test(p)) {
				block.accept(p);
			}
		}
	}

	private static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {
		for (Person p : roster) {
			if (tester.test(p)) {
				p.printPerson();
			}
		}
	}

	private static void printPersons(List<Person> roster, CheckPerson tester) {
		for (Person p : roster) {
			if (tester.test(p)) {
				p.printPerson();
			}
		}
	}

	private static void approach2_printPersonsWithinBirthRange(List<Person> roster,
			LocalDate low,
			LocalDate high) {
		for (Person p : roster) {
			if (p.getBirthday().isAfter(low) && p.getBirthday().isBefore(high)) {
				p.printPerson();
			}
		}
	}

	private static void approach1_printPersonsOlderThan(List<Person> roster, LocalDate birthday) {
		
		for (Person p : roster) {
			if (p.getBirthday().isAfter(birthday)) {
				p.printPerson();
			}
		}
	}
}
