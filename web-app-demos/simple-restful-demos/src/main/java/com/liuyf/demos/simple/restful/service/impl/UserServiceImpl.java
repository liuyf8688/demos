package com.liuyf.demos.simple.restful.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liuyf.demos.simple.restful.dao.UserDao;
import com.liuyf.demos.simple.restful.model.UserModel;
import com.liuyf.demos.simple.restful.model.UserModel.USER_TYPE;
import com.liuyf.demos.simple.restful.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<UserModel> findByType(USER_TYPE userType) {
		return userDao.findByType(userType);
	}

}
