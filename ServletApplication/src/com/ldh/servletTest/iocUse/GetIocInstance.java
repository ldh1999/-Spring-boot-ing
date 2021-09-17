package com.ldh.servletTest.iocUse;

import com.ldh.ant.iocWirdAnt.Resource;
import com.ldh.ant.antConption.Spring;
import com.ldh.servletTest.virtualConfigPage.VirtualJdbc;

@Spring(name="iocInstance")
public class GetIocInstance {

    @Resource(name = "jdbcTemple")
    public VirtualJdbc virtualJdbc;

}
