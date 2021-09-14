package com.ldh.BeanFactor.BeanFatorHome;

public class AntChooseFactor {

    /**
     * 选出存在annotaion注解的类 并返回其实例
     * @param clazz 需要被判断且实例化的类
     * @param annotation 注解
     * @return 实例化对象
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Object chooseAnt(Class clazz, Class annotation) throws IllegalAccessException, InstantiationException {
        if (clazz.isAnnotationPresent(annotation)){
            return clazz.newInstance();
        }
        return null;
    }

}
