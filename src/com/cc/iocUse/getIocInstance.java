package com.cc.iocUse;

import com.ldh.ant.Resource;
import com.ldh.ant.Spring;

@Spring(name="iocInstance")
public class getIocInstance {
    @Resource(name = "beana")
    private String str;
}
