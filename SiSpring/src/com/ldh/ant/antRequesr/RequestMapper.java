package com.ldh.ant.antRequesr;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapper {
    String path();
    String method();
}
