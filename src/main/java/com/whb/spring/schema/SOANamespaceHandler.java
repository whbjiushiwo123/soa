package com.whb.spring.schema;

import com.whb.spring.Protocol;
import com.whb.spring.Reference;
import com.whb.spring.Registry;
import com.whb.spring.Service;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class SOANamespaceHandler extends NamespaceHandlerSupport {
    public void init(){
        this.registerBeanDefinitionParser("service",new ServiceBeanDefinitionParser(Service.class));
        this.registerBeanDefinitionParser("protocol",new ProtocolBeanDefinitionParser(Protocol.class));
        this.registerBeanDefinitionParser("registry",new RegistryBeanDefinitionParser(Registry.class));
        this.registerBeanDefinitionParser("reference",new ReferenceBeanDefinitionParser(Reference.class));
    }
}
