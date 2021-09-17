package com.ldh.ant.iocWirdAnt;
import java.lang.annotation.*;


/**
 * 当前resource只提供根据name获取ioc中的对象
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Resource {
    /**
     * name目标为在注入bean时的方法名
     * @return
     */
    public String name();
}
