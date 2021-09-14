package com.ldh.Factor;

public interface InstanceFactor {

    Object getInstance(Class clazz);

    void addClass(Class clazz);

    Object getClassByName(String clazzName);

    boolean addInstance(Object value, String key);

    Object getInstanceByKey(String key);

}
