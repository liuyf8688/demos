package com.liuyf.demos.javaee.jpa.elementcollection.string.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.liuyf.demos.javaee.jpa.elementcollection.string.dao.CompanyDao;
import com.liuyf.demos.javaee.jpa.elementcollection.string.entity.Company;
import com.liuyf.demos.spring.test.dbunit.dao.AbstractDaoTest;

public class CompanyDaoTest extends AbstractDaoTest {

	@Autowired
	private CompanyDao companyDao;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	@DatabaseSetup({ "/test-data/company/company.xml" })
	public void testList() {
		List<Company> companies = companyDao.list();
		assertEquals(2, companies.size());
	}

	@Test
	@DatabaseSetup({ "/test-data/company/company.xml", "/test-data/company/company-phones.xml", "/test-data/company/company-emails.xml" })
	public void testGetById() {
		Company company = companyDao.getById(1L);
		Assert.assertNotNull(company);
		assertEquals(2, company.getPhones().size());
		assertEquals(2, company.getEmails().size());
	}

}
