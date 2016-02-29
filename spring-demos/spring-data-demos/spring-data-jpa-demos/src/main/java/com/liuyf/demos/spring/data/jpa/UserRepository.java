package com.liuyf.demos.spring.data.jpa;

import com.liuyf.demos.spring.data.jpa.dao.MyBaseRepository;
import com.liuyf.demos.spring.data.jpa.pojo.UserPojo;

public interface UserRepository extends MyBaseRepository<UserPojo, Long> {

}
