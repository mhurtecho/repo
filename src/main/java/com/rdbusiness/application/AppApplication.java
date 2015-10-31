package com.rdbusiness.application;

import javax.annotation.Priority;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;

@ApplicationPath("rest")
@Priority(value = 1)
public class AppApplication extends ResourceConfig implements WebApplicationInitializer {

	public AppApplication() {
		//jersey configuration
		packages("com.rdbusiness.rest.endpoint");
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		//spring configuration
		servletContext.setInitParameter("contextConfigLocation", "/WEB-INF/beans.xml");
		servletContext.addListener(ContextLoaderListener.class);
	}
}
