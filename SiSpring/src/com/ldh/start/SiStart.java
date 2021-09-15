package com.ldh.start;

import com.cc.iocUse.GetIocInstance;
import com.ldh.BeanFactor.BeanFactor;
import com.ldh.BeanFactor.BeanFactorImpl.BeanFactorAllImpl;
import com.ldh.BeanFactor.BeanFatorHome.BeanListAll;
import com.ldh.ioc.BeanList;
import com.ldh.ioc.IocImpl.IocLIst.SpringForList;
import com.ldh.ioc.IocInit;
import com.ldh.packageScan.PackageScanner;
import com.ldh.packageScan.packageScanImpl.PackageScannerImpl;

import java.util.logging.Logger;


public class SiStart {

    //日志
    private final static Logger logger = Logger.getGlobal();

    private static PackageScanner packageScanner;

    private static BeanListAll beanListAll;

    private static IocInit iocInit;

    private SiStart(){}

    /**
     * 创建实例化bean工厂，创建ioc容器
     */
    private static void beanListAllInstance(){
        BeanFactor beanFactor = new BeanFactorAllImpl();
        beanListAll = (BeanListAll)beanFactor.getBeanFactor();
    }
    public static void start(String pack){
        logger.info("SiSpring启动中");
        //实例化包扫描
        packageScanner = new PackageScannerImpl(pack);
        beanListAllInstance();
        //将扫描到的类放入ioc容器
        try {
            //将扫描到的Class放入beanListAll中
            beanListAll.setListClassAll(packageScanner.getFullQualifiedClass());
            //实例话ioc方法
            //初始化ioc
            iocInit = new IocInit(beanListAll.getListClassAll());
            logger.info("SiSpring启动完成");
            //获取所有BeanMap

            //测试
            /*BeanList beanList = BeanForList.getBeanForList();
            BeanAll beanAll = (BeanAll) beanList.getBean("beanAll");
            System.out.println(beanAll.beana());*/
            //测试
            /*BeanList beanList1 = SpringForList.getBeanForList();
            GetIocInstance getIocInstance = (GetIocInstance) beanList1.getBean("iocInstance");
            System.out.println(getIocInstance.getStr());*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
