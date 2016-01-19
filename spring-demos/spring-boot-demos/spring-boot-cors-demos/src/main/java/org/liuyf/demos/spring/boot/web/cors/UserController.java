package org.liuyf.demos.spring.boot.web.cors;

import java.util.Arrays;
import java.util.List;

import org.liuyf.demos.spring.boot.web.cors.model.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/springboot/users")
public class UserController {

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable Long id) {
		
		User user = new User();
		user.setId(id);
		user.setFirstName("Yanfeng");
		user.setLastName("Liu");
		user.setAge("0");
		
		return user;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<User> getUsers() {
		
		User user = new User();
		user.setFirstName("Yanfeng");
		user.setLastName("Liu");
		user.setAge("0");
		
		return Arrays.asList(user);
	}
}
