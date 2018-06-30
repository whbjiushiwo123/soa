package com.whb.spring;

import com.whb.spring.registry.BaseRegistry;
import com.whb.spring.registry.RedisRegistry;
import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class Registry  implements Serializable  ,FactoryBean {
    private String protocol;
    private String address;
    private static Map<String,BaseRegistry> registryMap = new HashMap<>();

    static {
        registryMap.put("redis",new RedisRegistry());
    }


    @Override
    public Object getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public static Map<String, BaseRegistry> getRegistryMap() {
        return registryMap;
    }

    public static void setRegistryMap(Map<String, BaseRegistry> registryMap) {
        Registry.registryMap = registryMap;
    }
}
