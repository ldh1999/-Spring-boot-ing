package com.ldh.ioc;

import java.util.List;
import java.util.Map;

public interface IocOutInstance {
    Object iocResource(Object obj, Map map) throws IllegalAccessException;
}
