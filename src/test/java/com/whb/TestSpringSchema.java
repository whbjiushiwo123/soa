package com.whb;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringSchema {
    @Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        /*UserService userService = context.getBean(UserService.class);
        userService.eate();*/
    }
}
