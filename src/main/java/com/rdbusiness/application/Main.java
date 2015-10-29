package com.rdbusiness.application;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.JettyWebXmlConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;


public class Main {

	public static void main(String[] args) throws Exception {
		String webappDirLocation = "src/main/webapp/";

		Server server = new Server(8080);

		WebAppContext context = new WebAppContext();

		context.setContextPath("/app-zero");
		context.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
		context.setResourceBase(webappDirLocation);

		context.setConfigurations(new Configuration[] { new AnnotationConfiguration(), new WebInfConfiguration(),
				new WebXmlConfiguration(), new MetaInfConfiguration(), new FragmentConfiguration(),
				new EnvConfiguration(), new PlusConfiguration(), new JettyWebXmlConfiguration() });

		context.setParentLoaderPriority(true);

		server.setHandler(context);

		server.start();
		server.join();
	}

}
