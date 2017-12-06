package com.samples.rae;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Configuration
public class EventClientBeanRegistrar {

    private final Logger logger = Logger.getLogger(EventClientBeanRegistrar.class.getName());

    public EventClientBeanRegistrar(ApplicationContext applicationContext) {

        ClassPathScanningCandidateComponentProvider classpathScanner = new ClassPathScanningCandidateComponentProvider(false);
        classpathScanner.addIncludeFilter(new AnnotationTypeFilter(EventListener.class));

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        try {

            for (BeanDefinition beanDefinition : classpathScanner.findCandidateComponents("com.samples.rae")) {

                Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());

                EventListener eventListener = clazz.getAnnotation(EventListener.class);

                String beanName = ClassUtils.getShortNameAsProperty(clazz);

                Method[] methods = applicationContext.getBean(beanName).getClass().getMethods();

                //noinspection ResultOfMethodCallIgnored
                Arrays.stream(methods).filter(method -> method.isAnnotationPresent(EventMapping.class))
                        .peek(method -> {
                            logger.info("Mapped [Topic:"+eventListener.topic()+" -> EventMapping: /"+method.getAnnotation(EventMapping.class).event()+" (Method: "+method.getDeclaringClass().getName()+"."+method.getName()+")]");
                            ConsumerDataStore.register(eventListener.topic(), method.getAnnotation(EventMapping.class).event(), method, beanDefinition);
                        }).collect(Collectors.toList());

            }

            context.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
