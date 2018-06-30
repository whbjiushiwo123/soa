package com.whb.spring.proxy;

import lombok.Data;

import java.lang.reflect.Method;

@Data
public class Invocation {
    private Method method;
    private Object[] objects;

}
