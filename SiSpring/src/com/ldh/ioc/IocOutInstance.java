package com.ldh.ioc;

import com.ldh.springException.SpringIocExpetion;

import java.util.Map;

public interface IocOutInstance {
    Object iocResource(Object obj, Map map) throws IllegalAccessException, SpringIocExpetion;
}
