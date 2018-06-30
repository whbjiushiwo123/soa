package com.whb.spring;

import com.whb.spring.registry.BaseRegistryDelegate;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;

@Data
public class Service implements InitializingBean {
    private String intf;
    private String ref;
    private String protocol;
    private static ApplicationContext applicationContext;

    /**
     * 使用委托，代码变活了
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        BaseRegistryDelegate.registry(ref,applicationContext);
    }
}
