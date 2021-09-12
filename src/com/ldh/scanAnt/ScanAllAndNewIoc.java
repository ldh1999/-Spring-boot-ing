package com.ldh.scanAnt;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 *
 */
public interface ScanAllAndNewIoc {
    Map<String, Object> getBean();

    Map<String, Object> getAllMap() throws IllegalAccessException, InvocationTargetException, InstantiationException;

    public void initIOC() throws IllegalAccessException, InstantiationException, InvocationTargetException;
}
