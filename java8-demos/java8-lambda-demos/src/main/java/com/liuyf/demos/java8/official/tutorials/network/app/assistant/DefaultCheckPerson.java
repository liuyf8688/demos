package com.liuyf.demos.java8.official.tutorials.network.app.assistant;

import com.liuyf.demos.java8.official.tutorials.network.app.Person;
import com.liuyf.demos.java8.official.tutorials.network.app.Person.Sex;

public class DefaultCheckPerson implements CheckPerson {

	@Override
	public boolean test(Person p) {
		return p.getGender() == Sex.MALE;
	}

}
