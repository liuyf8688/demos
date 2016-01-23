package com.liuyf.demos.spring.test.dbunit.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.liuyf.demos.spring.test.dbunit.listener.ForeignKeyDisabling;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/config/config.xml" })
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	DbUnitTestExecutionListener.class,
	ForeignKeyDisabling.class
})
public abstract class AbstractDaoTest {

}
