package com.samples.rae;

import java.lang.annotation.*;


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventMapping {

    String event();
    String[] dispatchTo() default {};

}
