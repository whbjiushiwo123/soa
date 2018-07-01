package com.whb.spring;

import com.whb.spring.configBean.BaseConfigBean;
import com.whb.spring.registry.BaseRegistryDelegate;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Data
public class Service extends BaseConfigBean implements InitializingBean,ApplicationContextAware {
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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Service.applicationContext = applicationContext;
    }


    public static ApplicationContext getApplication() {
        return applicationContext;
    }

}
