package com.rdbusiness.application;

import java.net.URL;
import java.security.ProtectionDomain;
import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.FilterMapping;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.eclipse.jetty.webapp.Configuration.ClassList;
import org.eclipse.jetty.webapp.WebAppContext;


public class EmbeddedWar {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		ClassList classlist = ClassList.setServerDefault(server);
		classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration",
				"org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration");
		classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
				"org.eclipse.jetty.annotations.AnnotationConfiguration");

		WebAppContext context = new WebAppContext();

		context.setContextPath("/");

		ProtectionDomain protectionDomain = EmbeddedWar.class.getProtectionDomain();
		URL location = protectionDomain.getCodeSource().getLocation();
		context.setWar(location.toExternalForm());

		// context.setTempDirectory(new File("tempApp"));

		ServletHolder servlet = new ServletHolder();

		servlet.setHeldClass(DefaultServlet.class);
		servlet.setInitParameter("dirAllowed", "false");

		context.addServlet(servlet, "/");

		FilterHolder holder = new FilterHolder(CrossOriginFilter.class);
		holder.setName("cross-origin");
		FilterMapping fm = new FilterMapping();
		fm.setFilterName("cross-origin");
		fm.setPathSpec("/rest/*");

		context.addFilter(holder, "/rest/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));

		// FilterHolder holder = new FilterHolder(CrossOriginFilter.class);
		// holder.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM,
		// "*");
		// holder.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER,
		// "*");
		// holder.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM,
		// "GET,POST,HEAD");
		// holder.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM,
		// "X-Requested-With,Content-Type,Accept,Origin");
		// holder.setName("cross-origin");
		// FilterMapping fm = new FilterMapping();
		// fm.setFilterName("cross-origin");
		// fm.setPathSpec("*");
		// handler.addFilter(holder, fm);

		server.setHandler(context);

		server.start();
		server.join();
	}

}
