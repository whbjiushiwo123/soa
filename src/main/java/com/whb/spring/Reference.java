package com.whb.spring;

import com.whb.spring.proxy.HttpInvoke;
import com.whb.spring.proxy.Invoke;
import com.whb.spring.proxy.advice.InvokeInvocationHandler;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Data
@Log
public class Reference  implements Serializable  ,FactoryBean ,ApplicationContextAware{
    private String id;
    private String intf;
    private String protocol;
    private String loadBalance;
    private Invoke invoke;
    private ApplicationContext applicationContext;
    private static Map<String,Invoke> invokes = new HashMap<String, Invoke>();
    static {
        invokes.put("http",new HttpInvoke());
        invokes.put("rmi",null);
        invokes.put("netty",null);
    }
    public Reference(){
        log.info("Reference is called !");
    }
    /**
     * 拿到一个实例 这个方法是spring调用，spring初始化的时候，具体是getBean方法里面调用
     * ApplicationContext.getBean("id")
     * getObject 这个方法的返回值，会交给spring容器管理
     * 在getObject 这个方法里面，返回的intf这个接口的代理
     * @return
     * @throws Exception
     */
    @Override
    public Object getObject() throws Exception {
        log.info("返回 getObject 代理对象");
        if(protocol != null && !"".equals(protocol)){
            invoke = invokes.get(protocol);
        }else{
            //Protocol 这个实例实在spring 容器中
            Protocol protocol = applicationContext.getBean(Protocol.class);
            if(protocol != null){
                invoke = invokes.get(protocol.getName());
            }else {
                invoke = invokes.get("http");
            }
        }
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class<?>[]{Class.forName(intf)},
                new InvokeInvocationHandler(invoke,this));
    }

    @Override
    public Class<?> getObjectType() {
        log.info("返回 getObjectType 代理对象");
        if(intf != null || !"" .equals( intf)){
            try {
                return Class.forName(intf);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
