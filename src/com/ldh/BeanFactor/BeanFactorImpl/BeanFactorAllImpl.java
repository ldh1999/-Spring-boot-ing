package com.ldh.BeanFactor.BeanFactorImpl;

import com.ldh.BeanFactor.BeanFactor;

public class BeanFactorAllImpl implements BeanFactor {
    @Override
    public Object getBeanFactor() {
        return BeanListAll.getBeanListAll();
    }
}
