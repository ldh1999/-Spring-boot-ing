package com.ldh.Factor;

import java.util.List;

public interface InstanceFactor {

    Object getInstance(Class clazz);

    void addClass(Class clazz);

    Object getClassByName(String clazzName);

}
