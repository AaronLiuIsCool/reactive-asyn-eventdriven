package com.samples.rae;

import org.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

public class EventHandlerMap {

    private String action;
    private BeanDefinition beanDefinition;
    private Method method;

    public EventHandlerMap() {
    }

    public EventHandlerMap(String action, BeanDefinition beanDefinition, Method method) {
        this.action = action;
        this.beanDefinition = beanDefinition;
        this.method = method;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public BeanDefinition getBeanDefinition() {
        return beanDefinition;
    }

    public void setBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinition = beanDefinition;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
