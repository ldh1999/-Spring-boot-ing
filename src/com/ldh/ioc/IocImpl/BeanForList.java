package com.ldh.ioc.IocImpl;

import com.ldh.BeanFactor.BeanFactor;
import com.ldh.BeanFactor.BeanFactorImpl.BeanFactorAllImpl;
import com.ldh.BeanFactor.BeanFatorHome.AntChooseFactor;
import com.ldh.ant.Bean;
import com.ldh.ant.Config;
import com.ldh.ioc.BeanList;
import com.ldh.util.StringUtilss;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanForList implements BeanList {

    /**
     * 工厂和类选择器
     */
    private BeanFactor beanFactor;
    private AntChooseFactor antChooseFactor;

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
        //实例化bean工厂 使用工厂创建出类选择器
        beanFactor = new BeanFactorAllImpl();
        antChooseFactor = (AntChooseFactor) beanFactor.getAntChooseFactor();

        this.beanMap = new HashMap();
        this.configInstanceMap = new HashMap();
        this.listClass = listClass;
    }

    public synchronized static BeanForList getBeanForList(){
        if (beanForListInstance == null){
            System.err.println("请注入ioc");
            return null;
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
    private Object scanIocToInstance(String name){
        if (beanMap.get(name) != null){
            return beanMap.get(name);
        }else {
            return null;
            //这里需要走异常

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
    private void chooseConfigAnt(Class clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object obj = antChooseFactor.chooseAnt(clazz, Config.class);
        if (obj == null){
            return;
        }
        Config config = (Config)clazz.getAnnotation(Config.class);
        if (StringUtilss.isEmpty(config.name())){
            System.err.println("Config name is null");
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
    public Object getBean(String name) {
        if (StringUtilss.isEmpty(name)||configInstanceMap.get(name)==null){
            System.err.println("当前bean为空");
            return null;
        }
        return configInstanceMap.get(name);
    }

    @Override
    public void initIOC() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if (listClass == null){
            return ;
        }
        //循环扫描到的所有类注解
        for (Class clazz : listClass){
            //Config注解类
             chooseConfigAnt(clazz);
        }
    }
}
