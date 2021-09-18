package com.cc;

import com.cc.virtualConfigPage.BBB;
import com.ldh.ant.iocWirdAnt.Autowird;
import com.ldh.ant.antConption.Spring;
import com.ldh.start.SiStart;

@Spring(name = "Spring")
public class StartApplication {
    //测试
    @Autowird
    private static BBB virtualJdbc;

    @Autowird
    private static String str;

    public static void main(String[] args){
        SiStart.start("com.cc");
        virtualJdbc.start();
        System.out.println(str);


        /*try {
            ServiceForList serviceForList = ServiceForList.getBeanForList();
            System.out.println(serviceForList.getBean("VirtualService"));
        } catch (SpringIocExpetion springIocExpetion) {
            springIocExpetion.printStackTrace();
        }*/
    }
}
