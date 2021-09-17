package com.ldh.ioc.IocUtils;

import com.ldh.BeanFactor.BeanFatorHome.AntChooseFactor;
import com.ldh.ioc.IocImpl.IocLIst.BeanForList;
import com.ldh.ioc.IocOutInstance;
import com.ldh.ioc.iocWirdImpl.IocOutInstanceForResource;
import com.ldh.springException.SpringIocExpetion;
import com.ldh.statueFinal.InstanceType;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IocChooseAntUtils {
    /**
     * 处理Map<String,List<Object>> 的数据(注意！！！！！！！！！！！！！！！)
     * @param map 传来的ioc容器
     * @param ant 需要判断的注解
     * @param clazz 需要注入的class实例
     * @param antChooseFactor 注解选择器工厂方法
     */

    private static IocOutInstance iocOutInstance;

    private static BeanForList beanForList;

    static{
        iocOutInstance = new IocOutInstanceForResource();
        try {
            beanForList = BeanForList.getBeanForList();
        } catch (SpringIocExpetion springIocExpetion) {
            springIocExpetion.printStackTrace();
        }
    }

    public static void chooseAntWriteIocMap(Map<String,List<Object>> listMap, Class clazz, Class ant, AntChooseFactor antChooseFactor) throws InstantiationException, IllegalAccessException, SpringIocExpetion {
        Object obj = antChooseFactor.chooseAnt(clazz, ant);
        if (obj == null){
            return;
        }
        //注入带有resource注解的属性(只针对obj对象)
        iocOutInstance.iocResource(obj, beanForList.getBeanMap(), InstanceType.LIST);
        //注入带有autowrid注解的属性(只针对obj对象)
        iocOutInstance.iocAutowrid(obj, beanForList.getBeanMap());


        if (listMap.containsKey(clazz.getName())){
            listMap.get(clazz.getName()).add(obj);
        }else {
            List<Object> list = new LinkedList<>();
            list.add(obj);
            listMap.put(clazz.getName().substring(clazz.getName().lastIndexOf('.')+1), list);
        }
    }


}
