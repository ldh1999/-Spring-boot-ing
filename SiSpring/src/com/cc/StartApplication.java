package com.cc;

import com.cc.virtualConfigPage.VirtualJdbc;
import com.ldh.ant.Resource;
import com.ldh.ant.antConption.Service;
import com.ldh.ant.antConption.Spring;
import com.ldh.ioc.IocImpl.IocLIst.ServiceForList;
import com.ldh.springException.SpringIocExpetion;
import com.ldh.start.SiStart;

@Service()
public class StartApplication {
    //测试
    @Resource(name = "jdbcTemple")
    private static VirtualJdbc virtualJdbc;

    public static void main(String[] args){
        SiStart.start("com.cc");
        virtualJdbc.start();
        try {
            ServiceForList serviceForList = ServiceForList.getBeanForList();
            System.out.println(serviceForList.getBean("VirtualService"));
        } catch (SpringIocExpetion springIocExpetion) {
            springIocExpetion.printStackTrace();
        }
    }
}
