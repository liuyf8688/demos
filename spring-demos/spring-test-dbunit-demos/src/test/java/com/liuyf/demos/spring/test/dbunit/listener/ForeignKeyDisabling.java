package com.liuyf.demos.spring.test.dbunit.listener;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class ForeignKeyDisabling extends AbstractTestExecutionListener {

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		IDatabaseConnection dbConn = new DatabaseDataSourceConnection(testContext.getApplicationContext().getBean(DataSource.class));
		dbConn.getConnection().prepareStatement("SET DATABASE REFERENTIAL INTEGRITY FALSE").execute();
	}
	
}
