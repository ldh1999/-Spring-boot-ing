package com.ldh.packageScan.packageScanImpl;

import com.ldh.packageScan.PackageScanner;
import com.ldh.util.StringUtil;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * 扫描给定的包
 */
public class PackageScannerImpl implements PackageScanner {

    private String basePage;
    private ClassLoader classLoader;
    private List<String> listClassAllName;
    private List<Class> listClassAll;

    public PackageScannerImpl(String basePage) {
        this.basePage = basePage;
        this.classLoader = getClass().getClassLoader();
        this.listClassAllName = new LinkedList<>();
        this.listClassAll = new LinkedList<>();
    }

    public PackageScannerImpl(String basePage, ClassLoader classLoader) {
        this.basePage = basePage;
        this.classLoader = classLoader;
        this.listClassAllName = new LinkedList<>();
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
                listClassAllName.add(className);
            }else {
                this.doScan(basePackage+'.'+className,new ArrayList<>());
            }
        }
        return listClassAllName;
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

    private List<Class> docanToClass(String basePage) throws ClassNotFoundException {
        String basePath = StringUtil.dotToSplash(basePage);
        URL url = classLoader.getResource(basePath);
        String urlStr = StringUtil.getRootPath(url);
        File file = new File(urlStr);
        if (!file.exists()||!file.isDirectory()){
            return null;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = file.listFiles(new FileFilter() {
            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            @Override
            public boolean accept(File file) {
                return (file.isDirectory()) || (file.getName().endsWith(".class"));
            }
        });
        for (File file_ : dirfiles){
            if (file_.isDirectory()){
                docanToClass(basePage+'.'+file_.getName());
            }else{
                String className = file_.getName().substring(0,file_.getName().lastIndexOf('.'));
                listClassAll.add(Class.forName(basePage+'.'+className));
            }
        }
        return listClassAll;
    }

    @Override
    public List<Class> getFullQualifiedClass() throws ClassNotFoundException {
        return docanToClass(basePage);
    }

    @Override
    public List<String> getFullyQualifiedClassNameList() throws IOException {
        return doScan(basePage,new ArrayList<>());
    }
}
