package com.liuyf.demos.simple.restful.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AFilter implements Filter {

	private static final Log log = LogFactory.getLog(AFilter.class);
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		log.debug("=============================: " + AFilter.class);
	}

}
