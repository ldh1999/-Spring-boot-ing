package com.ldh.Factor.FactorImpl;

import com.ldh.Factor.InstanceFactor;

import java.lang.reflect.Constructor;

public class FactorEasy implements InstanceFactor {

    private Object object;

    @Override
    public Object getInstance(Class clazz) {
        try{
            Constructor constructor = clazz.getConstructor();
            object = constructor.newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return object;
    }
}
