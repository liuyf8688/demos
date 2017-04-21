package com.liuyf.demos.simple.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liuyf.demos.simple.restful.dao.UserDao;
import com.liuyf.demos.simple.restful.mappers.UserMapper;
import com.liuyf.demos.simple.restful.model.UserModel;
import com.liuyf.demos.simple.restful.model.UserModel.USER_TYPE;
import com.liuyf.demos.simple.restful.pojo.UserPojo;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public List<UserModel> findByType(USER_TYPE userType) {
		List<UserPojo> pojos = userMapper.findByType(userType.getType());
		
		List<UserModel> models = new ArrayList<>();
		UserModel model = null;
		for (UserPojo pojo : pojos) {
			model = new UserModel();
			model.setId(pojo.getId());
			model.setUsername(pojo.getUsername());
			model.setAge(pojo.getAge());
			models.add(model);
		}
		
		return models;
	}
}
