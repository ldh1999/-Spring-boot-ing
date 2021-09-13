package com.ldh.ioc;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface BeanList {
    Object getBean(String name);

    void initIOC() throws IllegalAccessException, InstantiationException, InvocationTargetException;
}
