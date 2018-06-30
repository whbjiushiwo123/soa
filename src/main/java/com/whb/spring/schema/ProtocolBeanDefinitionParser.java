package com.whb.spring.schema;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class ProtocolBeanDefinitionParser implements BeanDefinitionParser {

    private Class<?> beanClass;
    public ProtocolBeanDefinitionParser(Class<?> beanClass ){
        this.beanClass = beanClass;
    }


    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setLazyInit(false);
        beanDefinition.setBeanClass(beanClass);

        String name=element.getAttribute("name");
        String host=element.getAttribute("host");
        String port = element.getAttribute("port");

        if(port == null || "".equals(port)){
            throw new RuntimeException("Protocal protocol is not null");
        }
        if(host == null || "".equals(host)){
            throw new RuntimeException("Protocal host is not null");
        }
        if(name == null || "".equals(name)){
            throw new RuntimeException("Protocal name is not null");
        }

        beanDefinition.getPropertyValues().addPropertyValue("name",name);
        beanDefinition.getPropertyValues().addPropertyValue("host",host);
        beanDefinition.getPropertyValues().addPropertyValue("port",port);
        parserContext.getRegistry().registerBeanDefinition("protocol",beanDefinition);
        return beanDefinition;
    }
}
