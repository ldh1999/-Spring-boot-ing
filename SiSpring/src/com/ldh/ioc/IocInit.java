package com.ldh.ioc;

import com.ldh.ioc.IocImpl.IocLIst.BeanForList;
import com.ldh.ioc.IocImpl.IocLIst.ControllerForList;
import com.ldh.ioc.IocImpl.IocLIst.SpringForList;
import com.ldh.springException.SpringIocExpetion;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Logger;

public class IocInit {

    //日志
    private final static Logger logger = Logger.getGlobal();

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
     * controllerList
     */
    private ControllerForList controllerForList;

    /**
     * 初始化当前ioc总容器
     * @param listClass
     */
    public IocInit(List<Class> listClass) throws IllegalAccessException, InstantiationException, InvocationTargetException, SpringIocExpetion {
        this.listClass = listClass;
        this.initALLIocList();
    }

    /**
     * 初始化所有的ioc容器
     */
    private void initALLIocList() throws IllegalAccessException, InvocationTargetException, InstantiationException, SpringIocExpetion {
        //注意顺序！！！！！！！！
        logger.info("IOC容器初始化");
        beanForList = BeanForList.getBeanForList(listClass);
        beanForList.initIOC();
        springForList = SpringForList.getBeanForList(listClass);
        springForList.initIOC();
        controllerForList = ControllerForList.getBeanForList(listClass);
        controllerForList.initIOC();
        logger.info("IOC容器初始化完成");
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

    public ControllerForList getControllerForList() {
        return controllerForList;
    }

    public void setControllerForList(ControllerForList controllerForList) {
        this.controllerForList = controllerForList;
    }
}
