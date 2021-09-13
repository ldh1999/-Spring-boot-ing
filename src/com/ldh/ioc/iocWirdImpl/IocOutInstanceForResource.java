package com.ldh.ioc.iocWirdImpl;

import com.ldh.ant.Resource;
import com.ldh.ioc.IocOutInstance;

import java.lang.reflect.Field;
import java.util.Map;

public class IocOutInstanceForResource implements IocOutInstance {
    @Override
    public Object iocResource(Object obj, Map map) throws IllegalAccessException {
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            if (field.isAnnotationPresent(Resource.class)){
                Resource resource = field.getAnnotation(Resource.class);
                String beanName = resource.name();
                Object beanInstance = map.get(beanName);
                if (beanInstance == null) {
                    System.err.println("ioc注入失败，name不存在");
                }else {
                    field.setAccessible(true);
                    field.set(obj, beanInstance);
                }
            }
        }
        return obj;
    }
}
