package com.ldh.ioc.IocImpl.IocLIst;

import com.ldh.ant.Bean;
import com.ldh.ant.Config;
import com.ldh.ioc.IocImpl.IocForList;
import com.ldh.springException.SpringIocExpetion;
import com.ldh.util.StringUtilss;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanForList extends IocForList {
    /**
     * 当前ioc中的bean
     */
    private Map<String, Object> beanMap;

    /**
     * 当前ioc中的config实例
     */
    private Map<String, Object> configInstanceMap;

    private static BeanForList beanForListInstance;

    /**
     * 包下所有类
     */
    private List<Class> listClass;

    private BeanForList(List<Class> listClass) {
        super(listClass);
        this.beanMap = new HashMap();
        this.configInstanceMap = new HashMap();
        this.listClass = listClass;
    }

    public synchronized static BeanForList getBeanForList() throws SpringIocExpetion {
        if (beanForListInstance == null){
            throw new SpringIocExpetion("请注入ioc");
        }
        return beanForListInstance;
    }

    public synchronized static BeanForList getBeanForList(List<Class> listClass){
        if (beanForListInstance == null){
            beanForListInstance = new BeanForList(listClass);
        }
        return beanForListInstance;
    }

    /**
     * 便利ioc,返回识别到的bean
     * @return
     */
    private Object scanIocToInstance(String name) throws SpringIocExpetion {
        if (beanMap.get(name) != null){
            return beanMap.get(name);
        }else {
            throw new SpringIocExpetion("ioc容器中不存在这个name");
        }
    }

    /**
     * 将当前Bean注解加入到ioc
     * @param method 当前扫描到的方法
     * @param obj 类的实例
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void joinBeanForBeanList(Method method, Object obj) throws InvocationTargetException, IllegalAccessException {
        beanMap.put(method.getName(),method.invoke(obj));
    }

    /**
     * 过滤所有的带@Config类
     * @param clazz 需判断的类
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    private void chooseConfigAnt(Class clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException, SpringIocExpetion {
        Object obj = antChooseFactor.chooseAnt(clazz, Config.class);
        if (obj == null){
            return;
        }
        Config config = (Config)clazz.getAnnotation(Config.class);
        if (StringUtilss.isEmpty(config.name())){
            throw new SpringIocExpetion("Config name is null");
        }else {
            configInstanceMap.put(config.name(), obj);
        }
        Method[] methods = clazz.getMethods();
        for(Method method: methods){
            //扫描所有带Bean注解的方法
            if (method.isAnnotationPresent(Bean.class)){
                //将带bean注解的方法注入到ioc
                joinBeanForBeanList(method, obj);
            }
        }
    }

    public Map<String, Object> getBeanMap(){
        return this.beanMap;
    }

    @Override
    public Object getBean(String name) throws SpringIocExpetion {
        if (StringUtilss.isEmpty(name)||configInstanceMap.get(name)==null){
            throw new SpringIocExpetion("当前bean为空");
        }
        return configInstanceMap.get(name);
    }

    @Override
    public void initIOC() throws IllegalAccessException, InstantiationException, InvocationTargetException, SpringIocExpetion {
        if (listClass == null){
            return ;
        }
        //循环扫描到的所有类注解
        for (Class clazz : listClass){
            //Config注解类
             chooseConfigAnt(clazz);
             logger.info(clazz+"注入成功");
        }
    }
}
