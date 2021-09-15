package com.ldh.ant.antConption;

import com.sun.istack.internal.NotNull;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Service{
    @NotNull
    String name();
}
