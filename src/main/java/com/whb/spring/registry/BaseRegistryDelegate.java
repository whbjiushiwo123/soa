package com.whb.spring.registry;

import com.whb.spring.Registry;
import org.springframework.context.ApplicationContext;

/**
 * 委托者模式
 */
public class BaseRegistryDelegate {
    public static void registry(String ref, ApplicationContext applicationContext){
        Registry registry = applicationContext.getBean(Registry.class);
        String protocol = registry.getProtocol();
        BaseRegistry baseRegistry = Registry.getRegistryMap().get(protocol);
        baseRegistry.registry(ref,applicationContext);
    }
}
