package com.ldh.ioc.IocImpl;

import com.ldh.BeanFactor.BeanFactor;
import com.ldh.BeanFactor.BeanFactorImpl.BeanFactorAllImpl;
import com.ldh.BeanFactor.BeanFatorHome.AntChooseFactor;
import com.ldh.ant.Spring;
import com.ldh.ioc.BeanList;
import com.ldh.ioc.IocOutInstance;
import com.ldh.ioc.iocWirdImpl.IocOutInstanceForResource;
import com.ldh.util.StringUtilss;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpringForList implements BeanList {

    /**
     * 工厂和类选择器
     */
    private BeanFactor beanFactor;
    private AntChooseFactor antChooseFactor;

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
    /**
     * 包下所有类
     */
    private List<Class> listClass;

    private static SpringForList springForListInstance;

    private SpringForList(List<Class> listClass) {
        //实例化bean工厂 使用工厂创建出类选择器
        beanFactor = new BeanFactorAllImpl();
        antChooseFactor = (AntChooseFactor) beanFactor.getAntChooseFactor();
        //实例化注解选择器
        iocOutInstance = new IocOutInstanceForResource();
        //实例化ioc中单例bean容器
        beanForList = BeanForList.getBeanForList();

        springInstanceMap = new HashMap<>();
        this.listClass = listClass;
    }

    public synchronized static SpringForList getBeanForList(){
        if (springForListInstance == null){
            System.err.println("请注入ioc");
            return null;
        }
        return springForListInstance;
    }

    public synchronized static SpringForList getBeanForList(List<Class> listClass){
        if (springForListInstance == null){
            springForListInstance = new SpringForList(listClass);
        }
        return springForListInstance;
    }

    private void chooseSpringAnt(Class clazz) throws InstantiationException, IllegalAccessException {
        Object obj = antChooseFactor.chooseAnt(clazz, Spring.class);
        if (obj == null){
            return;
        }
        Spring spring = (Spring)clazz.getAnnotation(Spring.class);
        if (StringUtilss.isEmpty(spring.name())){
            System.err.println("Spring name is null");
        }else {
            iocOutInstance.iocResource(obj,beanForList.getBeanMap());
            springInstanceMap.put(spring.name(), obj);
        }

    }

    @Override
    public Object getBean(String name) {
        return springInstanceMap.get(name);
    }

    @Override
    public void initIOC() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if (listClass == null){
            return ;
        }
        for (Class clazz : listClass){
            chooseSpringAnt(clazz);
        }
    }
}
