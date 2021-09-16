package com.ldh.BeanFactor.BeanFactorImpl;

import com.ldh.BeanFactor.BeanFactor;
import com.ldh.BeanFactor.BeanFatorHome.AntChooseFactor;
import com.ldh.BeanFactor.BeanFatorHome.BeanListAll;
import com.ldh.BeanFactor.BeanFatorHome.IocBeanWrid;
import com.ldh.springException.SpringIocExpetion;

public class BeanFactorAllImpl implements BeanFactor {
    @Override
    public Object getBeanFactor() {
        return BeanListAll.getBeanListAll();
    }

    @Override
    public Object getAntChooseFactor() {
        return new AntChooseFactor();
    }

    @Override
    public Object getIocBeanWirdFactor() throws SpringIocExpetion {
        return new IocBeanWrid();
    }
}
