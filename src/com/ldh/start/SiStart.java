package com.ldh.start;

import com.ldh.BeanFactor.BeanFactor;
import com.ldh.BeanFactor.BeanFactorImpl.BeanFactorAllImpl;
import com.ldh.BeanFactor.BeanFactorImpl.BeanListAll;
import com.ldh.packageScan.PackageScanner;
import com.ldh.packageScan.packageScanImpl.PackageScannerImpl;
import com.ldh.scanAnt.ScanAllAndNewIoc;
import com.ldh.scanAnt.scanAntImpl.ScanAllAndNewIocImpl;

import java.util.Map;


public class SiStart {

    private static PackageScanner packageScanner;


    private static BeanListAll beanListAll;

    private SiStart(){}

    /**
     * 创建实例化bean工厂，创建ioc容器
     */
    private static void beanListAllInstance(){
        BeanFactor beanFactor = new BeanFactorAllImpl();
        beanListAll = (BeanListAll)beanFactor.getBeanFactor();
    }
    public static void start(String pack){
        //实例化包扫描
        packageScanner = new PackageScannerImpl(pack);
        beanListAllInstance();
        //将扫描到的类放入ioc容器
        try {
            //将扫描到的Class放入beanListAll中
            beanListAll.setListClassAll(packageScanner.getFullQualifiedClass());
            //实例话ioc方法
            ScanAllAndNewIoc iocScan = new ScanAllAndNewIocImpl(beanListAll.getListClassAll());
            //初始化ioc
            iocScan.initIOC();
            //获取所有BeanMap
            Map<String,Object> beanAllMap = iocScan.getAllMap();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
