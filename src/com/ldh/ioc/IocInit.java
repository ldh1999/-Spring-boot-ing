package com.ldh.ioc;

import com.ldh.ioc.IocImpl.BeanForList;
import com.ldh.ioc.IocImpl.SpringForList;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class IocInit {

    /**
     * 包下所有类
     */
    private List<Class> listClass;

    /**
     * beanList
     */
    private BeanForList beanForList;

    /**
     * springList
     */
    private SpringForList springForList;

    /**
     * 初始化当前ioc总容器
     * @param listClass
     */
    public IocInit(List<Class> listClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        this.listClass = listClass;
        this.initALLIocList();
    }

    /**
     * 初始化所有的ioc容器
     */
    private void initALLIocList() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        beanForList = BeanForList.getBeanForList(listClass);
        beanForList.initIOC();
        springForList = SpringForList.getBeanForList(listClass);
        springForList.initIOC();
    }

    public List<Class> getListClass() {
        return listClass;
    }

    public void setListClass(List<Class> listClass) {
        this.listClass = listClass;
    }

    public BeanList getBeanForList() {
        return beanForList;
    }

    public void setBeanForList(BeanForList beanForList) {
        this.beanForList = beanForList;
    }

    public SpringForList getSpringForList() {
        return springForList;
    }

    public void setSpringForList(SpringForList springForList) {
        this.springForList = springForList;
    }
}
