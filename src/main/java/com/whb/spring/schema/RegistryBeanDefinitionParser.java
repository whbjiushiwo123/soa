package com.whb.spring.schema;

import lombok.extern.java.Log;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

@Log
public class RegistryBeanDefinitionParser implements BeanDefinitionParser {

    private Class<?> beanClass;

    public RegistryBeanDefinitionParser(Class<?> beanClass){
        this.beanClass = beanClass;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        log.info("解析RegistryBeanDefinitionParser开始");
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);
        String protocol = element.getAttribute("protocol");
        log.info("解析RegistryBeanDefinitionParser开始 protocol="+protocol);
        String address = element.getAttribute("address");
        log.info("解析RegistryBeanDefinitionParser开始 address"+address);

        if(protocol == null || "".equals(protocol)){
            throw new RuntimeException("Registry protocol is not null");
        }
        if(address == null || "".equals(address)){
            throw new RuntimeException("Registry address is not null");
        }
        beanDefinition.getPropertyValues().addPropertyValue("protocol",protocol);
        beanDefinition.getPropertyValues().addPropertyValue("address",address);
        parserContext.getRegistry().registerBeanDefinition("registry",beanDefinition);

        log.info("解析RegistryBeanDefinitionParser结束");
        return beanDefinition;
    }
}
