package com.rdbusiness.application;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.springframework.web.context.ContextLoaderListener;

import com.rdbusiness.rest.endpoint.Endpoint;

public class EmbeddedJar {

	public static void main(String[] args) throws Exception {

		Server server = new Server(8080);
		ServletContextHandler handler = new ServletContextHandler();

		handler.setContextPath("/");

		handler.addEventListener(new ContextLoaderListener());
		handler.setInitParameter("contextConfigLocation", "classpath*:**/beans.xml");

		ServletHolder jettyConfig = handler.addServlet(DefaultServlet.class, "/");

		jettyConfig.setInitParameter("dirAllowed", "false");

		handler.addFilter(CrossOriginFilter.class, "/rest/*",
				EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));

		ServletHolder jerseyConfig = handler.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/rest/*");

		jerseyConfig.setInitParameter("jersey.config.server.provider.packages", Endpoint.class.getPackage().getName());
		jerseyConfig.setInitOrder(0);

		server.setHandler(handler);

		try {
			server.start();
			server.join();
		} finally {
			server.destroy();
		}
	}

}
