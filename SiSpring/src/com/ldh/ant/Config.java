package com.ldh.ant;

import com.sun.istack.internal.NotNull;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Config {
    @NotNull
    String name();
}
