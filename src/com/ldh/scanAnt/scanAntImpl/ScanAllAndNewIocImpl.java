package com.ldh.scanAnt.scanAntImpl;

import com.ldh.ant.Bean;
import com.ldh.ant.Config;
import com.ldh.ant.Resource;
import com.ldh.ant.Spring;
import com.ldh.scanAnt.ScanAllAndNewIoc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ScanAllAndNewIocImpl implements ScanAllAndNewIoc {


    /**
     * 当前ioc中的所有带注释的类
     */
    private Map<String, Object> mapAllAnt;

    /**
     * 当前ioc中的bean
     */
    private Map<String, Object> beanMap;

    /**
     * 当前ioc中的Spring业务
     */
    private Map<String, Object> springMap;

    /**
     * 包下所有类
     */
    private List<Class> listClass;

    public ScanAllAndNewIocImpl(List<Class> listClass){
        this.listClass = listClass;
        beanMap = new HashMap<>();
        mapAllAnt = new HashMap<>();
        springMap = new HashMap<>();
    }

    /**
     * 过滤所有的带@Config类
     * @param clazz 需判断的类
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    private void chooseConfigAnt(Class clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if (clazz.isAnnotationPresent(Config.class)){
            Object obj = clazz.newInstance();
            Method[] methods = clazz.getMethods();
            for(Method method: methods){
                //扫描所有带Bean注解的方法
                if (method.isAnnotationPresent(Bean.class)){
                    //将带bean注解的方法注入到ioc
                    joinBeanForBeanList(method, obj);
                }
            }

        }
    }

    /**
     * 过滤所有的带@Spring类,并为其创建对象放入ioc中
     * @param clazz 需判断的类
     */
    private void chooseSpringAnt(Class clazz) throws IllegalAccessException, InstantiationException {
        if (clazz.isAnnotationPresent(Spring.class)){
            Spring spring = (Spring)clazz.getAnnotation(Spring.class);
            springMap.put(spring.name(),clazz.newInstance());
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields){
                if (!field.isAnnotationPresent(Resource.class)){
                    continue;
                }
                Resource resourceValue = field.getDeclaredAnnotation(Resource.class);
                if (resourceValue.name() != null){
                    Object obj = scanIocToInstance(resourceValue.name());
                    if (obj != null){
                        //将属性值注入属性
                        field.setAccessible(true);
                        field.set();
                    }
                }
            }
        }
    }

    /**
     * 缺少容器上层方法
     * @param name
     * @return
     */
    private Object findInstanceByIoc(String name){
        //tooth
        return null;
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

    //获取所有带注解类的方法 然后进行分类 存入上述容器中
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
        mapAllAnt.put("BEAN",beanMap);
    }

    @Override
    public Map<String, Object> getBean() {
        return beanMap;
    }

    /**
     * 返回ioc中的所有带注解类的map
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Override
    public Map<String, Object> getAllMap() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return mapAllAnt;
    }
}
