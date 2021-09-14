package com.ldh.packageScan;

import java.io.IOException;
import java.util.List;

public interface PackageScanner {
    public List<String> getFullyQualifiedClassNameList() throws IOException;

    public List<Class> getFullQualifiedClass() throws ClassNotFoundException;
}
