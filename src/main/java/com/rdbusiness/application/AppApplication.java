package com.rdbusiness.application;

import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;

import javax.annotation.Priority;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.ws.rs.ApplicationPath;

@ApplicationPath(Config.PATH)
@Priority(value = 1)
public class AppApplication extends ResourceConfig implements WebApplicationInitializer {

	private static final Logger LOG = Log.getLogger(AppApplication.class);

	public AppApplication() {
		LOG.info("Jersey configuration: PATH[" + Config.PATH + "], " + "PACKAGE[" + Config.PACKAGE + "]");
		packages(Config.PACKAGE);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		LOG.info("Spring configuration: " + Config.SPRING_CONFIG);
		servletContext.setInitParameter("contextConfigLocation", Config.SPRING_CONFIG);
		servletContext.addListener(ContextLoaderListener.class);
	}
}

