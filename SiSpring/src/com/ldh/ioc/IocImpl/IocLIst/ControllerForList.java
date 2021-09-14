package com.ldh.ioc.IocImpl.IocLIst;

import com.ldh.ant.Controller;
import com.ldh.ioc.IocImpl.IocForList;
import com.ldh.springException.SpringIocExpetion;
import com.ldh.util.StringUtilss;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ControllerForList extends IocForList {
    /**
     * ioc中的controller集合
     */
    private Map<String, List<Object>> controllerInstanceMap;

    /**
     * 当前的单例类
     */
    private static ControllerForList controllerForList;

    private ControllerForList(List<Class> classList){
        super(classList);
        controllerInstanceMap = new HashMap<>();
    }


    private void chooseControllerAnt(Class clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException, SpringIocExpetion {
        Object obj = antChooseFactor.chooseAnt(clazz, Controller.class);
        if (obj == null){
            return;
        }
        if (controllerInstanceMap.containsKey(clazz.getName())){
            controllerInstanceMap.get(clazz.getName()).add(obj);
        }else {
            List<Object> list = new LinkedList<>();
            list.add(obj);
            controllerInstanceMap.put(clazz.getName(), list);
        }

    }

    public synchronized static ControllerForList getBeanForList() throws SpringIocExpetion {
        if (controllerForList == null){
            throw new SpringIocExpetion("请注入ioc");
        }
        return controllerForList;
    }

    public synchronized static ControllerForList getBeanForList(List<Class> listClass){
        if (controllerForList == null){
            controllerForList = new ControllerForList(listClass);
        }
        return controllerForList;
    }

    @Override
    public Object getBean(String name) throws SpringIocExpetion {
        List<Object> list = controllerInstanceMap.get(name);
        if (StringUtilss.isEmpty(name)||list == null){
            throw new SpringIocExpetion("当前bean为空");
        }
        if (list.size()>1){
            System.err.println("注意,注入"+name+"出现二义性");
        }
        return controllerInstanceMap.get(name).get(0);
    }

    @Override
    public void initIOC() throws IllegalAccessException, InstantiationException, InvocationTargetException, SpringIocExpetion {
        if (listClass == null){
            return ;
        }
        //循环扫描到的所有类注解
        for (Class clazz : listClass){
            chooseControllerAnt(clazz);
            logger.info(clazz+"注入成功");
        }
    }
}
