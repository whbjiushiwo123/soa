package com.whb.spring.schema;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class ServiceBeanDefinitionParser implements BeanDefinitionParser {
    private Class<?> beanClass;
    public ServiceBeanDefinitionParser(Class<?> beanClass){
        this.beanClass = beanClass;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setLazyInit(false);
        beanDefinition.setBeanClass(beanClass);

        String intf=element.getAttribute("interface");
        String ref=element.getAttribute("ref");
        String protocol = element.getAttribute("protocol");
        if(protocol == null || "".equals(protocol)){
            throw new RuntimeException("Service protocol is not null");
        }
        if(intf == null || "".equals(intf)){
            throw new RuntimeException("Service intf is not null");
        }
        if(ref == null || "".equals(ref)){
            throw new RuntimeException("Service ref is not null");
        }
        beanDefinition.getPropertyValues().addPropertyValue("intf",intf);
        beanDefinition.getPropertyValues().addPropertyValue("ref",ref);
        beanDefinition.getPropertyValues().addPropertyValue("protocol",protocol);
        parserContext.getRegistry().registerBeanDefinition("service",beanDefinition);

        return beanDefinition;
    }
}
