package com.nakanara.core.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Target({ElementType.METHOD})
public @interface ApiInfo {

    String name() default "";
    String value() default "";

    String version() default "";
}
