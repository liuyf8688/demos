package com.liuyf.demos.spring.web.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuyf.demos.spring.web.model.User;

@Controller
@RequestMapping("/users")
public class UserController {

	@RequestMapping(path = "", method = RequestMethod.GET)
	@ResponseBody
	public List<User> listAllUsers() {
		
		List<User> users = new ArrayList<>();
		users.add(new User("Zhang San", 20));
		users.add(new User("Li Shi", 26));
		users.add(new User("Wang Wu", 23));
		users.add(new User("Zhao Liu", 15));
		
		return users;
	}
}
