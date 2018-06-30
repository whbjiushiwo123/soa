package com.whb.spring;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

import java.io.Serializable;

@Data
public class Protocol implements Serializable ,FactoryBean {
    private String host;
    private String Name;
    private String port;

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
}
