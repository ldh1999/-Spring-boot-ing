package com.cc.java.service;

import com.cc.java.virtualConfigPage.VirtualJdbc;
import com.ldh.ant.iocWirdAnt.Resource;
import com.ldh.ant.antConption.Service;

@Service()
public class VirtualService {
    @Resource(name = "jdbcTemple")
    private VirtualJdbc virtualJdbc;

}
