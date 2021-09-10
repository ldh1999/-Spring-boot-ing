package com.ldh.packageScan.packageScanImpl;

import com.ldh.packageScan.PackageScanner;
import com.ldh.util.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class PackageScannerImpl implements PackageScanner {

    private String basePage;
    private ClassLoader classLoader;
    private List<String> listClassAll;

    public PackageScannerImpl(String basePage) {
        this.basePage = basePage;
        this.classLoader = getClass().getClassLoader();
        this.listClassAll = new LinkedList<>();
    }

    public PackageScannerImpl(String basePage, ClassLoader classLoader) {
        this.basePage = basePage;
        this.classLoader = classLoader;
        this.listClassAll = new LinkedList<>();
    }
    private List<String> doScan(String basePackage, List<String> nameList) throws IOException {

        String pathScFirst = StringUtil.dotToSplash(basePackage);
        URL url = classLoader.getResource(pathScFirst);
        String urlStr = StringUtil.getRootPath(url);
        List<String> list = null;
        if (!this.isJar(urlStr)){
            list = this.readFromDirectory(urlStr);
        } else {
             list = readFromJarFile(urlStr, pathScFirst);
        }
        for (String className : list){
            if (this.isClass(className)){
                listClassAll.add(className);
            }else {
                this.doScan(basePackage+'.'+className,new ArrayList<>());
            }
        }
        return listClassAll;
    }

    private boolean isClass(String str){
        return str.endsWith(".class");
    }

    private boolean isJar(String str){
        return str.endsWith(".jar");
    }
    private List<String> readFromDirectory(String name){
        File file = new File(name);
        String[] names = file.list();
        if (names == null){
            return null;
        }else {
            return Arrays.asList(names);
        }
    }

    private List<String> readFromJarFile(String jarPath, String splashedPackageName) throws IOException {

        JarInputStream jarIn = new JarInputStream(new FileInputStream(jarPath));
        JarEntry entry = jarIn.getNextJarEntry();
        List<String> nameList = new ArrayList<String>();
        while (null != entry) {
            String name = entry.getName();
            if (name.startsWith(splashedPackageName) && isClass(name)) {
                nameList.add(name);
            }

            entry = jarIn.getNextJarEntry();
        }

        return nameList;
    }


    @Override
    public List<String> getFullyQualifiedClassNameList() throws IOException {
        return doScan(basePage,new ArrayList<>());
    }

    public static void main(String[] ages){

        PackageScanner packageScanner= new PackageScannerImpl("com.ldh");
        try{
            List<String> list = packageScanner.getFullyQualifiedClassNameList();
            list.forEach((e)->{
                System.out.println(e);
            });
        }catch (IOException e){
            e.getMessage();
        }

    }
}
