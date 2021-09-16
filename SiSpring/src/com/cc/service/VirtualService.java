package com.cc.service;

import com.cc.virtualConfigPage.VirtualJdbc;
import com.ldh.ant.Resource;
import com.ldh.ant.antConption.Service;

@Service()
public class VirtualService {
    @Resource(name = "jdbcTemple")
    private VirtualJdbc virtualJdbc;

}
