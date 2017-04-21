package com.liuyf.demos.simple.restful.dao;

import java.util.List;

import com.liuyf.demos.simple.restful.model.UserModel;
import com.liuyf.demos.simple.restful.model.UserModel.USER_TYPE;

public interface UserDao {

	List<UserModel> findByType(USER_TYPE userType);

}
