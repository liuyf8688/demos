package com.liuyf.demos.simple.restful.service;

import java.util.List;

import com.liuyf.demos.simple.restful.model.UserModel;
import com.liuyf.demos.simple.restful.model.UserModel.USER_TYPE;

public interface UserService {

	List<UserModel> findByType(USER_TYPE userType);
}
