package com.ldh.ioc;

import com.ldh.springException.SpringIocExpetion;

import java.lang.reflect.InvocationTargetException;

public interface BeanList {
    Object getBean(String name) throws SpringIocExpetion;
    void initIOC() throws IllegalAccessException, InstantiationException, InvocationTargetException, SpringIocExpetion;
}
