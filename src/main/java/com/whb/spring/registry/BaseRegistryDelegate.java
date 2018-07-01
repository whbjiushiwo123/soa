package com.whb.spring.registry;

import com.whb.spring.Registry;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * 委托者模式
 */
public class BaseRegistryDelegate {
    //注册
    public static void registry(String ref, ApplicationContext applicationContext){
        Registry registry = applicationContext.getBean(Registry.class);
        String protocol = registry.getProtocol();
        BaseRegistry baseRegistry = Registry.getRegistryMap().get(protocol);
        baseRegistry.registry(ref,applicationContext);
    }

    /**
     * 拿到注册内容
     * 先获取注册中心，然后拿到协议，根据协议拿到对应的BaseRegistry
     *
     */

    public static List<String> getRegistry(String id,ApplicationContext applicationContext){
        Registry registry = applicationContext.getBean(Registry.class);
        String protocol = registry.getProtocol();
        BaseRegistry registryBean = Registry.getRegistryMap().get(protocol);
        return registryBean.getRegistry(id,applicationContext);
    }
}
