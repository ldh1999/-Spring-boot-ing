package com.ldh.BeanFactor.BeanFatorHome;

public class AntChooseFactor {

    public Object chooseAnt(Class clazz, Class annotation) throws IllegalAccessException, InstantiationException {
        if (clazz.isAnnotationPresent(annotation)){
            return clazz.newInstance();
        }
        return null;
    }

}
