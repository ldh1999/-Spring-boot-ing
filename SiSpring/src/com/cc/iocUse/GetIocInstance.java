package com.cc.iocUse;

import com.cc.virtualConfigPage.VirtualJdbc;
import com.ldh.ant.iocWirdAnt.Resource;
import com.ldh.ant.antConption.Spring;

@Spring(name="iocInstance")
public class GetIocInstance {

    @Resource(name = "jdbcTemple")
    public VirtualJdbc virtualJdbc;

}
