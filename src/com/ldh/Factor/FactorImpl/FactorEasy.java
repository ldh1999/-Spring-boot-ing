package com.ldh.Factor.FactorImpl;

import com.ldh.Factor.InstanceFactor;
import com.ldh.util.StringUtilss;
import com.sun.xml.internal.ws.util.StringUtils;


import java.lang.reflect.Constructor;
import java.util.List;

public class FactorEasy implements InstanceFactor {

    private List<Class> clazzList;

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

    @Override
    public void addClass(Class clazz) {
        clazzList.add(clazz);
    }

    @Override
    public Object getClassByName(String clazzName) {
        if (StringUtilss.isEmpty(clazzName)){
            return false;
        }
        for(Class clazz : clazzList){
            if (clazz.getName().equals(clazzName)){
                return clazz;
            }
        }
        return false;
    }
}
