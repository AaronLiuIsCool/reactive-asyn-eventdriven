package com.samples.rae;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

public final class ConsumerDataStore {

    private static final MultiValueMap<String, EventHandlerMap> handlers = new LinkedMultiValueMap<>();

    public static MultiValueMap<String, EventHandlerMap> getHandlers() {
        return handlers;
    }

    public static List<Method> getMappingFor(String topic, String action) {
        return handlers.get(topic).stream()
                .filter(eventHandlerMap -> eventHandlerMap.getAction().equals(action))
                .map(EventHandlerMap::getMethod)
                .collect(Collectors.toList());
    }

    public static void register(String topic, String action, Method method, BeanDefinition beanDefinition) {
        EventHandlerMap eventHandlerMap = new EventHandlerMap(action, beanDefinition, method);
        handlers.add(topic, eventHandlerMap);
    }

}
