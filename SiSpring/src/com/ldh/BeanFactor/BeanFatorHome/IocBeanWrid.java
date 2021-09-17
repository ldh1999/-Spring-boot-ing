package com.ldh.BeanFactor.BeanFatorHome;

import com.ldh.ant.iocWirdAnt.Resource;
import com.ldh.ioc.IocImpl.IocLIst.BeanForList;
import com.ldh.springException.SpringIocExpetion;

import java.lang.reflect.Field;
import java.util.Map;

public class IocBeanWrid {


    private Map<String, Object> beanForListMap;

    public IocBeanWrid() throws SpringIocExpetion {
        this.beanForListMap = BeanForList.getBeanForList().getBeanMap();
    }

    public void wridBean(Object object, Class ant) throws IllegalAccessException {
        Field[] fields = object.getClass().getFields();
        for (Field field : fields){
            if (!field.isAnnotationPresent(ant)){
                continue;
            }
            Resource resource = field.getAnnotation(Resource.class);
            Object instance= beanForListMap.get(resource.name());
            field.set(object, instance);
        }
    }

}
