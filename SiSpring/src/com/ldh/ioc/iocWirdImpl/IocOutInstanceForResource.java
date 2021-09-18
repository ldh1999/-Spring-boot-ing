package com.ldh.ioc.iocWirdImpl;

import com.ldh.ant.iocWirdAnt.Autowird;
import com.ldh.ant.iocWirdAnt.Resource;
import com.ldh.ioc.IocOutInstance;
import com.ldh.springException.SpringIocExpetion;

import java.lang.reflect.Field;
import java.util.*;

public class IocOutInstanceForResource implements IocOutInstance {
    /**
     * 往实例中的带有Resource的字段注入传来的ioc容器中的对应值
     * @param obj 需要修改字段属性值的对象
     * @param map 需要被提取的ioc容器
     * @param instanceType （类型判断）
     * @return
     * @throws IllegalAccessException
     * @throws SpringIocExpetion
     */
    @Override
    public Object iocResource(Object obj, Map map,String instanceType) throws IllegalAccessException, SpringIocExpetion {
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            if (field.isAnnotationPresent(Resource.class)){
                //获取当前方法的Resource注解
                Resource resource = field.getAnnotation(Resource.class);

                //获取注解中的name
                String beanName = resource.name();
                //获取此name在传入的ioc实例中的对象
                Object beanInstance = map.get(beanName);
                //当传来的Map值为对象时
                //if (instanceType.equals(InstanceType.MAP)){
                    if (beanInstance == null) {
                        throw new SpringIocExpetion("ioc注入失败，name不存在");
                    }else {
                        field.setAccessible(true);
                        //更改此对象中的属性值
                        field.set(obj, beanInstance);
                    }
                    //当传来的Map值为List<Instance>时
                /*}else if (instanceType.equals(InstanceType.LIST)){
                    if (beanInstance == null) {
                        throw new SpringIocExpetion("ioc注入失败，name不存在");
                    }else {
                        List<Object> listInstance = (List)beanInstance;
                        for (Object instanceReal : listInstance){
                            field.setAccessible(true);
                            //更改此对象中的属性值
                            field.set(obj, instanceReal);
                        }
                    }
                }*/
            }
        }
        return obj;
    }
    //效率不太好，回头在优化

    @Override
    public Object iocAutowrid(Object obj, Map map) throws SpringIocExpetion, IllegalAccessException {
        Class clazz = obj.getClass();
        //判断该类是否有属性
        if (clazz.getDeclaredFields().length <= 0){
            return obj;
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            //判断当前方法是否有Autowrid注解
            if (!field.isAnnotationPresent(Autowird.class)){
                continue;
            }
            List<Object> collectInstanceObject = new LinkedList<>();
            //获取当前ioc中的所有值
            Collection<Object> mapValue = map.values();
            //便利所有值
            for (Object mapObject : mapValue){
                Class fieldClass = field.getType();
                //若ioc中的属性类型与该属性类一样则加入
                if (mapObject.getClass() == fieldClass){
                    collectInstanceObject.add(mapObject);
                }else{
                    //递归获取mapObject类型的所有父类信息
                    List<Class> listClass = new LinkedList<>();
                    //递归当前ioc选中类型的所有父类
                    this.getClassAllFatherClassAndInterfaces(mapObject.getClass(), listClass);
                    //遍历所有父类
                    for (Class fatherClass : listClass){
                        //若等于则加入，准备注入
                        if (fatherClass == fieldClass)  {
                            collectInstanceObject.add(mapObject);
                        }
                    }
                }
            }
            if (collectInstanceObject.isEmpty()){
                throw new SpringIocExpetion("ioc属性注入失败,该类型不存在");
            }else if (collectInstanceObject.size() == 1){
                //注入当前值
                field.setAccessible(true);
                field.set(obj, collectInstanceObject.get(0));
            }else if (collectInstanceObject.size() > 1){
                throw new SpringIocExpetion("无法找到唯一类型的值,建议使用resource注解根据name注入");
            }
        }
        return obj;
    }

    /**
     * 递归出传入类的所有父类
     * @param baseClass 传入类
     * @param classList 递归容器
     */
    private void getClassAllFatherClassAndInterfaces(Class baseClass, List<Class> classList){
        Class[] classInterfaces = baseClass.getInterfaces();
        Class classFatherClass = baseClass.getSuperclass();
        if (classInterfaces.length>0){
            for (Class classFather : classInterfaces){
                classList.add(classFather);
                getClassAllFatherClassAndInterfaces(classFather, classList);
            }
        }
        if (classFatherClass!=null){
            classList.add(classFatherClass);
            getClassAllFatherClassAndInterfaces(classFatherClass, classList);
        }

    }
}
