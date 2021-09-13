package com.cc.config;

import com.ldh.ant.Bean;
import com.ldh.ant.Config;

@Config(name = "beanAll")
public class BeanAll {
    /**
     * 默认反射关键字为方法名
     * @return
     */
    @Bean
    public String beana(){
            return "str";
        }
}
