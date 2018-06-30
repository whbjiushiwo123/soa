package com.whb.spring;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

import java.io.Serializable;

@Data
public class User  implements Serializable ,FactoryBean{
    private String id;
    private String name;
    private String sex;
    private int age;

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
