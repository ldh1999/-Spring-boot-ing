package com.ldh.ioc;

import com.ldh.springException.SpringIocExpetion;

import java.util.Map;

public interface IocOutInstance {
    Object iocResource(Object obj, Map map, String instanceType) throws IllegalAccessException, SpringIocExpetion;

    Object iocAutowrid(Object obj, Map map) throws SpringIocExpetion, IllegalAccessException;
}
