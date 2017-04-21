package com.liuyf.demos.simple.restful.web.teacher.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuyf.demos.simple.restful.model.UserModel;
import com.liuyf.demos.simple.restful.model.UserModel.USER_TYPE;
import com.liuyf.demos.simple.restful.service.UserService;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

	private static final Log log = LogFactory.getLog(TeacherController.class);
	
	@Value("${defaultHeaderName}")
	private String headerName;
	
	@Autowired
	UserService userService;
	
	@PostConstruct
	public void init() {
		log.info("+++++++++++++++++++ headerName +++++++++++++++++++ : " + headerName);
	}
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	@ResponseBody
	public List<UserModel> listAllTeachers() {
		
		return userService.findByType(USER_TYPE.TEARCHER);
	}
}
