package com.rdbusiness.rest.endpoint;

import com.rdbusiness.application.Config;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class EndpointTest extends JerseyTest {

    private ResourceConfig conf;

    @Override
    protected Application configure() {
        return conf = new ResourceConfig() {
            {
                packages(Config.PACKAGE);
                property("contextConfigLocation", Config.SPRING_CONFIG_TEST);
            }
        };
    }

    @Test
    public void testEndpoint() throws Exception {
        Class<?> clazz;
        Object object;
        Method method;
        Field field;

        for (Class<?> endpoint : conf.getClasses()) {
            clazz = Class.forName(endpoint.getCanonicalName() + "Test");

            object = clazz.newInstance();

            field = clazz.getDeclaredField("endpoint");
            field.setAccessible(true);
            field.set(object, this);

            method = clazz.getMethod("testCRUD", new Class<?>[]{});
            method.invoke(object, new Object[]{});
        }
    }

    public Response target(crud operation, String url, Entity<?> entity) {
        Response response = null;

        switch (operation) {
            case CREATE:
                response = target(url).request().post(entity);
                break;
            case DELETE:
                response = target(url).request().delete();
                break;
            case GET:
                break;
            case UPDATE:
                response = target(url).request().put(entity);
                break;
            default:
                break;
        }

        return response;
    }

    public <T> T target(String url, Class<T> clazz) {
        return target(url).request().get(clazz);
    }

    public enum crud {
        CREATE, UPDATE, DELETE, GET
    }

}
