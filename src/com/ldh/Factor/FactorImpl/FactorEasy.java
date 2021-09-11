package com.ldh.Factor.FactorImpl;

import com.ldh.Factor.InstanceFactor;
import com.ldh.util.StringUtilss;


import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FactorEasy implements InstanceFactor {

    private List<Class> clazzList;

    private Map<String, Object> mapInstance;

    private Object object;

    public FactorEasy(){
        mapInstance = new HashMap<>();
    }
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

    @Override
    public boolean addInstance(Object value, String key) {
        if (object == null){
            return false;
        }
        mapInstance.put(key, value);
        return true;
    }

    @Override
    public Object getInstanceByKey(String key) {
        if (mapInstance.containsKey(key)){
            return mapInstance.get(key);
        }
        return false;
    }
}
