package com.rdbusiness.application;

import com.rdbusiness.rest.endpoint.Endpoint;


public class Config {
	public static final String PATH = "rest";
	public static final String PACKAGE = Endpoint.class.getPackage().getName();
	public static final String SPRING_CONFIG = "classpath*:**/beans.xml";
    public static final String SPRING_CONFIG_TEST = "classpath*:**/beans_test.xml";
}
