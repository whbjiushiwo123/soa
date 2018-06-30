package com.whb.spring.proxy.advice;

import com.whb.spring.Reference;
import com.whb.spring.proxy.Invoke;
import lombok.extern.java.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * InvokeInvocationHandler 这个是一个advice，在这个advice里面进行rpc远程调用
 * rpc：http rmi netty
 */
@Log
public class InvokeInvocationHandler implements InvocationHandler{
    private Invoke invoke;
    private Reference reference;
    public InvokeInvocationHandler(Invoke invoke,Reference reference){
        this.invoke = invoke;
        this.reference = reference;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在这个invoke中调用多个远程Provider
        log.info("已经调用到");
        return null;
    }
}
