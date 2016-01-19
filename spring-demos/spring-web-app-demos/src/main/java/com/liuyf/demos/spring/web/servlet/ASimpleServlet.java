package com.liuyf.demos.spring.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebServlet(loadOnStartup = 0, value = "/asimpleservlet")
public class ASimpleServlet extends HttpServlet {

	private static final long serialVersionUID = 3493616808213635251L;
	private static final Log log = LogFactory.getLog(ASimpleServlet.class);
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		log.debug("======================: " + ASimpleServlet.class);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("======================: call " + ASimpleServlet.class + " api");
	}
	
	

}
