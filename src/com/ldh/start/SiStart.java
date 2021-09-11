package com.ldh.start;

import com.ldh.BeanFactor.BeanFactor;
import com.ldh.BeanFactor.BeanFactorImpl.BeanFactorAllImpl;
import com.ldh.BeanFactor.BeanFactorImpl.BeanListAll;
import com.ldh.packageScan.PackageScanner;
import com.ldh.packageScan.packageScanImpl.PackageScannerImpl;
import com.ldh.scanAnt.ScanAll;
import com.ldh.scanAnt.scanAntImpl.ScanAllImpl;

import java.util.List;
import java.util.Map;


public class SiStart {

    private static PackageScanner packageScanner;

    private static BeanListAll beanListAll;

    private SiStart(){}

    private static void beanListAllInstance(){
        BeanFactor beanFactor = new BeanFactorAllImpl();
        beanListAll = (BeanListAll)beanFactor.getBeanFactor();
    }
    public static void start(String pack){
        packageScanner = new PackageScannerImpl(pack);
        beanListAllInstance();
        try {
            beanListAll.setListClassAll(packageScanner.getFullQualifiedClass());
            ScanAll scanAll = new ScanAllImpl(beanListAll.getListClassAll());
            Map<String,Object> beanAllMap = scanAll.getAllMap();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
