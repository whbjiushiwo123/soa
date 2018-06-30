package com.whb.spring.registry;

import org.springframework.context.ApplicationContext;

public interface BaseRegistry {
    boolean registry(String param, ApplicationContext applicationContext);
}
