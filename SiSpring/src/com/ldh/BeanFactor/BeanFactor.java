package com.ldh.BeanFactor;

import com.ldh.springException.SpringIocExpetion;

/**
 * 为帮助操作ioc所需的工厂类
 */
public interface BeanFactor {
    Object getBeanFactor();

    Object getAntChooseFactor();

    Object getIocBeanWirdFactor() throws SpringIocExpetion;
}
