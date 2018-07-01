package com.whb.spring.registry;

import org.springframework.context.ApplicationContext;

import java.util.List;

public interface BaseRegistry {
    boolean registry(String param, ApplicationContext applicationContext);
    List<String> getRegistry(String id,ApplicationContext applicationContext);
}
