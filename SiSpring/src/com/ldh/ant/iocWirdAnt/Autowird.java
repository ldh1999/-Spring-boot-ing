package com.ldh.ant.iocWirdAnt;

import java.lang.annotation.*;


/**
 * 自动注入根据bean所提供自带的类型来注入，优先权暂定
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Autowird {
}
