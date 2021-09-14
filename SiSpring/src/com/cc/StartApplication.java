package com.cc;

import com.cc.virtualConfigPage.VirtualJdbc;
import com.ldh.ant.Resource;
import com.ldh.ant.Spring;
import com.ldh.start.SiStart;

@Spring(name = "sp")
public class StartApplication {
    //测试
    @Resource(name = "jdbcTemple")
    private static VirtualJdbc virtualJdbc;

    public static void main(String[] args){
        SiStart.start("com.cc");
        virtualJdbc.start();
    }
}
