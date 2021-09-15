package com.ldh.servletTest.config;

import com.ldh.ant.antConption.Bean;
import com.ldh.ant.Config;
import com.ldh.servletTest.virtualConfigPage.VirtualJdbc;

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

    @Bean
    public VirtualJdbc jdbcTemple(){
        VirtualJdbc virtualJdbc = new VirtualJdbc();
        virtualJdbc.setUrl("localhost:3306");
        virtualJdbc.setUsername("root");
        virtualJdbc.setPassword("123456");
        return virtualJdbc;
    }
}
