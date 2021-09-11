package com.ldh.scanAnt.scanAntImpl;

import com.ldh.Factor.FactorImpl.FactorEasy;
import com.ldh.Factor.InstanceFactor;
import com.ldh.ant.Bean;
import com.ldh.ant.Config;
import com.ldh.scanAnt.ScanAll;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ScanAllImpl implements ScanAll {


    /**
     * 当前ioc中的所有带注释的类
     */
    private Map<String, Object> mapAll;

    /**
     * 当前ioc中的bean
     */
    private List<Map<String, Object>> beanList;

    private List<Class> listClass;

    public ScanAllImpl(List<Class> listClass){
        this.listClass = listClass;
        beanList = new LinkedList<>();
        mapAll = new HashMap<>();
    }

    //获取所有带注解类的方法 然后进行分类 存入上述容器中
    private Map<String, Object> setEveryOne() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if (listClass == null){
            return null;
        }
        for (Class clazz : listClass){
            if (clazz.isAnnotationPresent(Config.class)){
                Object obj = clazz.newInstance();
                Method[] methods = clazz.getMethods();
                for(Method method: methods){
                    if (method.isAnnotationPresent(Bean.class)){
                        Map<String, Object> map = new HashMap<>();
                        map.put(method.getName(),method.invoke(obj));
                        beanList.add(map);
                    }
                }
            }
        }
        mapAll.put("BEAN",beanList);
        return mapAll;
    }
    
    @Override
    public void doBeanScan() {
        
    }

    @Override
    public Map<String, Object> getAllMap() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        setEveryOne();
        return mapAll;
    }
}
