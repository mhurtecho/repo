package com.rdbusiness.rest.service.proxy;

import com.rdbusiness.rest.bean.Bean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class ServiceProxy implements InvocationHandler {

    private Object obj;

    public static Object newInstance(Object obj) {
        return java.lang.reflect.Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new ServiceProxy(obj));
    }

    private ServiceProxy(Object obj) {
        this.obj = obj;
    }

    public Object invoke(Object proxy, Method m, Object[] args)
            throws Throwable {
        Object result;
        try {
            if ("create".equals(m.getName())) {
                for (Object arg : args) {
                    if (arg instanceof Bean) {
                        ((Bean) arg).getAudit().setCreateTime(System.currentTimeMillis());
                        break;
                    }
                }
            } else if ("update".equals(m.getName())) {
                for (Object arg : args) {
                    if (arg instanceof Bean) {
                        ((Bean) arg).getAudit().setModifyTime(System.currentTimeMillis());
                        break;
                    }
                }
            }

            result = m.invoke(obj, args);

        } catch (InvocationTargetException e) {
            throw e.getTargetException().getCause();
        } catch (Exception e) {
            throw e.getCause();
        }

        return result;
    }
}
