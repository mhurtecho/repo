package com.rdbusiness.application.spring;


import com.rdbusiness.rest.service.Service;
import com.rdbusiness.rest.service.proxy.ServiceProxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class AppSpringBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof Service) {
            return ServiceProxy.newInstance(bean);
        }

        return bean;
    }
}
