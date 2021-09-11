package com.ldh.scanAnt;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface ScanAll {
    void doBeanScan();

    Map<String, Object> getAllMap() throws IllegalAccessException, InvocationTargetException, InstantiationException;
}
