package com.liuyf.demos.simple.restful.mappers;

import java.util.List;

import com.liuyf.demos.simple.restful.pojo.UserPojo;

public interface UserMapper {

	List<UserPojo> findByType(int userType);
}
