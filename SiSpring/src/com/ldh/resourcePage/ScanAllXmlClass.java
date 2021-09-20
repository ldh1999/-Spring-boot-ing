package com.ldh.resourcePage;

import com.ldh.packageScan.PackageScanner;
import com.ldh.packageScan.packageScanImpl.PackageScannerImpl;
import com.ldh.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ScanAllXmlClass {

    private String path;
    private ClassLoader classLoader;

    public ScanAllXmlClass(String path){
       this.path = path;
       this.classLoader = getClass().getClassLoader();
    }
    public void scanXml() throws ParserConfigurationException, IOException, SAXException {
        String pathScFirst = StringUtil.dotToSplash(path);
        URL url = classLoader.getResource(pathScFirst+"/application.xml");
        String urlStr = StringUtil.getRootPath(url);
        File file = new File(urlStr);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        NodeList momentList = doc.getElementsByTagName("zz123z");


    }
}
