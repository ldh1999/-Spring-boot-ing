package com.ldh.Factor;

import java.util.List;

public interface InstanceFactor {

    Object getInstance(Class clazz);

    void addClass(Class clazz);

    Object getClassByName(String clazzName);

    boolean addInstance(Object value, String key);

    Object getInstanceByKey(String key);

}
