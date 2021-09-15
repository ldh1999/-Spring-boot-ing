package com.ldh.ioc.IocImpl.IocLIst;

import com.ldh.ant.antConption.Service;
import com.ldh.ioc.IocImpl.IocForList;
import com.ldh.ioc.IocUtils.IocChooseAntUtils;
import com.ldh.springException.SpringIocExpetion;
import com.ldh.util.StringUtilss;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceForList extends IocForList {

    /**
     * 当前ioc中的Service实例
     */
    private Map<String, List<Object>> serviceInstanceMap;

    /**
     * 当前的单例类
     */
    private static ServiceForList serviceForList;

    private ServiceForList(List<Class> listClass) {
        super(listClass);
        serviceInstanceMap = new HashMap();
    }

    /**
     * 单例模式
     * @return
     * @throws SpringIocExpetion
     */
    public synchronized static ServiceForList getBeanForList() throws SpringIocExpetion {
        if (serviceForList == null){
            throw new SpringIocExpetion("请注入ioc");
        }
        return serviceForList;
    }

    public synchronized static ServiceForList getBeanForList(List<Class> listClass){
        if (serviceForList == null){
            serviceForList = new ServiceForList(listClass);
        }
        return serviceForList;
    }

    private void chooseServiceAnt(Class clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException, SpringIocExpetion {
        IocChooseAntUtils.chooseAntWriteIocMap(serviceInstanceMap, clazz, Service.class, antChooseFactor);
    }



    @Override
    public Object getBean(String name) throws SpringIocExpetion {
        List<Object> list = serviceInstanceMap.get(name);
        if (StringUtilss.isEmpty(name)||list == null){
            throw new SpringIocExpetion("当前bean为空");
        }
        if (list.size()>1){
            System.err.println("注意,注入"+name+"出现二义性");
        }
        return serviceInstanceMap.get(name).get(0);
    }

    @Override
    public void initIOC() throws IllegalAccessException, InstantiationException, InvocationTargetException, SpringIocExpetion {
        if (listClass == null){
            return ;
        }
        //循环扫描到的所有类注解
        for (Class clazz : listClass){
            chooseServiceAnt(clazz);
            logger.info(clazz+"注入成功");
        }
    }
}
