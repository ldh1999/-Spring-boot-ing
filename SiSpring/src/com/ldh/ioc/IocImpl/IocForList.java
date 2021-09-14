package com.ldh.ioc.IocImpl;

import com.ldh.BeanFactor.BeanFactor;
import com.ldh.BeanFactor.BeanFactorImpl.BeanFactorAllImpl;
import com.ldh.BeanFactor.BeanFatorHome.AntChooseFactor;
import com.ldh.ioc.BeanList;
import com.ldh.springException.SpringIocExpetion;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Logger;


/**
 * iocR容器父类
 */
public abstract class IocForList implements BeanList {

    //日志
    protected final static Logger logger = Logger.getGlobal();

    /**
     * 工厂和类选择器
     */
    protected BeanFactor beanFactor;
    protected AntChooseFactor antChooseFactor;

    /**
     * 包下所有类
     */
    protected List<Class> listClass;

    public IocForList(List<Class> listClass) {
        //实例化bean工厂 使用工厂创建出类选择器
        this.beanFactor = new BeanFactorAllImpl();
        antChooseFactor = (AntChooseFactor) beanFactor.getAntChooseFactor();
        this.listClass = listClass;
    }

    @Override
    public abstract Object getBean(String name) throws SpringIocExpetion;

    @Override
    public abstract void initIOC() throws IllegalAccessException, InstantiationException, InvocationTargetException, SpringIocExpetion;
}
