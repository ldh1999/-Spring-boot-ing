package com.ldh.ioc.IocImpl.IocLIst;

import com.ldh.ant.antConption.Spring;
import com.ldh.ioc.IocImpl.IocForList;
import com.ldh.ioc.IocOutInstance;
import com.ldh.ioc.iocWirdImpl.IocOutInstanceForResource;
import com.ldh.springException.SpringIocExpetion;
import com.ldh.statueFinal.InstanceType;
import com.ldh.util.StringUtilss;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpringForList extends IocForList {


    /**
     * 注解选择器
     */
    private IocOutInstance iocOutInstance;

    /**
     * 当前ioc中的Spring实例
     */
    private Map<String, Object> springInstanceMap;

    /**
     * 当前ioc中的bean实例
     */
    private BeanForList beanForList;


    private static SpringForList springForListInstance;

    private SpringForList(List<Class> listClass) throws SpringIocExpetion {
        super(listClass);
        //实例化注解选择器
        iocOutInstance = new IocOutInstanceForResource();
        //实例化ioc中单例bean容器
        beanForList = BeanForList.getBeanForList();
        springInstanceMap = new HashMap<>();
    }

    public synchronized static SpringForList getBeanForList() throws SpringIocExpetion {
        if (springForListInstance == null){
            throw new SpringIocExpetion("请注入ioc");
        }
        return springForListInstance;
    }

    public synchronized static SpringForList getBeanForList(List<Class> listClass) throws SpringIocExpetion {
        if (springForListInstance == null){
            springForListInstance = new SpringForList(listClass);
        }
        return springForListInstance;
    }

    private void chooseSpringAnt(Class clazz) throws InstantiationException, IllegalAccessException, SpringIocExpetion {
        Object obj = antChooseFactor.chooseAnt(clazz, Spring.class);
        if (obj == null){
            return;
        }
        Spring spring = (Spring)clazz.getAnnotation(Spring.class);
        if (StringUtilss.isEmpty(spring.name())){
            throw new SpringIocExpetion("Spring name is null");
        }else {
            //注入带注解的属性值
            iocOutInstance.iocResource(obj,beanForList.getBeanMap(), InstanceType.MAP);
            iocOutInstance.iocAutowrid(obj, beanForList.getBeanMap());
            springInstanceMap.put(spring.name(), obj);
        }
    }

    @Override
    public Object getBean(String name) {
        return springInstanceMap.get(name);
    }

    @Override
    public void initIOC() throws IllegalAccessException, InstantiationException, InvocationTargetException, SpringIocExpetion {
        if (listClass == null){
            return ;
        }
        for (Class clazz : listClass){
            chooseSpringAnt(clazz);
            logger.info(clazz+"注入成功");
        }
    }
}
