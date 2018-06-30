package com.whb.spring.schema;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class ReferenceBeanDefinitionParser implements BeanDefinitionParser {
    private Class<?> beanClass;
    public ReferenceBeanDefinitionParser(Class<?> beanClass){
        this.beanClass = beanClass;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setLazyInit(false);
        beanDefinition.setBeanClass(beanClass);

        String id=element.getAttribute("id");
        String intf=element.getAttribute("intf");
        String protocol = element.getAttribute("protocol");
        String loadBalance = element.getAttribute("loadBalance");

        beanDefinition.getPropertyValues().addPropertyValue("id",id);
        beanDefinition.getPropertyValues().addPropertyValue("intf",intf);
        beanDefinition.getPropertyValues().addPropertyValue("protocol",protocol);
        beanDefinition.getPropertyValues().addPropertyValue("loadBalance",loadBalance);
        parserContext.getRegistry().registerBeanDefinition("reference",beanDefinition);

        return beanDefinition;
    }
}
