package com.ldh.ioc.IocUtils;

import com.ldh.BeanFactor.BeanFatorHome.AntChooseFactor;
import com.ldh.ant.antConption.Spring;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IocChooseAntUtils {
    /**
     * 处理Map<String,List<Object>> 的数据
     * @param map 传来的ioc容器
     * @param ant 需要判断的注解
     * @param clazz 需要注入的class实例
     * @param antChooseFactor 注解选择器工厂方法
     */
    public static void chooseAntWriteIocMap(Map<String,List<Object>> map, Class clazz, Class ant , AntChooseFactor antChooseFactor) throws InstantiationException, IllegalAccessException {
        Object obj = antChooseFactor.chooseAnt(clazz, ant);
        if (obj == null){
            return;
        }
        if (map.containsKey(clazz.getName())){
            map.get(clazz.getName()).add(obj);
        }else {
            List<Object> list = new LinkedList<>();
            list.add(obj);



            /*if (spAnt.name() != null){
                map.put(spAnt.name(), list);
            }else {*/
                map.put(clazz.getName().substring(clazz.getName().lastIndexOf('.')+1), list);
            //}
        }

    }
}
