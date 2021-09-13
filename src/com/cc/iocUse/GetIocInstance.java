package com.cc.iocUse;

import com.ldh.ant.Resource;
import com.ldh.ant.Spring;

@Spring(name="iocInstance")
public class GetIocInstance {
    @Resource(name = "beana")
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
