package com.liuyf.demos.spring.test.dbunit.listener;

import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class ForeignKeyDisablingListener extends AbstractTestExecutionListener {

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		Map<String, DataSource> dataSources = testContext.getApplicationContext().getBeansOfType(DataSource.class);
		for (Entry<String, DataSource> entry : dataSources.entrySet()) {
			IDatabaseConnection dbConn = new DatabaseDataSourceConnection(entry.getValue());
			dbConn.getConnection().prepareStatement("SET DATABASE REFERENTIAL INTEGRITY FALSE").execute();
		}
	}
	
}
