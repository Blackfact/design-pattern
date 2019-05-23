package com.blackfact.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Fruit {
    public String name() default "";
    public int type() default -1;
}
