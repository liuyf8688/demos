package com.liuyf.demos.spring.framework.web.teacher.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuyf.demos.spring.framework.web.model.User;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

	private static final Log log = LogFactory.getLog(TeacherController.class);
	
	@Value("${defaultHeaderName}")
	private String headerName;
	
	@PostConstruct
	public void init() {
		log.info("+++++++++++++++++++ headerName +++++++++++++++++++ : " + headerName);
	}
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	@ResponseBody
	public List<User> listAllTeachers() {
		
		List<User> users = new ArrayList<>();
		users.add(new User("Teacher Zhang", 20));
		users.add(new User("Teacher Shi", 26));
		
		return users;
	}
}
