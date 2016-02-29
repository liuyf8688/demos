package com.liuyf.demos.spring.data.jpa.dao;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface MyBaseRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

}
