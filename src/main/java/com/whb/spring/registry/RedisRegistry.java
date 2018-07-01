package com.whb.spring.registry;

import com.alibaba.fastjson.JSONObject;
import com.whb.spring.Protocol;
import com.whb.spring.Registry;
import com.whb.spring.Service;
import com.whb.spring.redis.RedisApi;
import lombok.extern.log4j.Log4j;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis 注册中心
 */
@Log4j
public class RedisRegistry implements BaseRegistry {
    @Override
    public boolean registry(String ref, ApplicationContext applicationContext) {
        try{
            Protocol protocol = applicationContext.getBean(Protocol.class);
            Map<String,Service> services = applicationContext.getBeansOfType(Service.class);
            Registry registry = applicationContext.getBean(Registry.class);
            RedisApi.createJedisPool(registry.getAddress());

            for(Map.Entry<String,Service> entry:services.entrySet()){
                if(entry.getValue().equals(ref)){
                    JSONObject jo = new JSONObject();
                    jo.put("protocol",JSONObject.toJSONString(protocol));
                    jo.put("service",JSONObject.toJSONString(entry.getValue()));
                    JSONObject ipport = new JSONObject();
                    ipport.put(protocol.getHost()+":"+protocol.getPort(),jo);
                    lpush(ipport,ref);
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }

    /**
     * json数据结果
     * "testServiceImpl":{
     *     {
     *         "host:port":{
     *             protocol:{},
     *             service:{}
     *         }
     *     },
     *     {
     *         "host:port":{
     *             protocol:{},
     *             service:{}
     *         }
     *     },
     *     {
     *         "host:port":{
     *             protocol:{},
     *             service:{}
     *         }
     *     }
     * }
     * @param ipport 多个服务信息
     * @param key 地址加端口
     */
    private void lpush(JSONObject ipport, String key) {
        if(RedisApi.exists(key)){
            Set<String> keys = ipport.keySet();
            String iportStr = "";
            //循坏取出host:port里面中的内容
            // {
            //    "host:port":{
            //        protocol:{},
            //        service:{}
            //   }
            // }

            for(String kk:keys){
                iportStr = kk;
            }
            List<String> registryInfo = RedisApi.lrange(key);
            List<String> newRegistry = new ArrayList<>();

            boolean isold = false;

            for(String node:registryInfo){
                JSONObject jo = JSONObject.parseObject(node);
                if(jo.containsKey(iportStr)){
                    newRegistry.add(ipport.toJSONString());
                    isold = true;
                }else{
                    newRegistry.add(node);
                }
            }

            if(isold){
                //老机器去重
                if(newRegistry.size()>0){
                    //删除老机器
                    RedisApi.del(key);
                    String[] newRestr = new String[newRegistry.size()];
                    for(int i=0;i<newRegistry.size();i++){
                        newRestr[i] = newRegistry.get(i);
                    }
                    RedisApi.lpush(key,newRestr);
                }
            }else{
                //加入新启动机器
                RedisApi.lpush(key,ipport.toJSONString());
            }
        }else{
            //所有的都是第一次启动
            RedisApi.lpush(key,ipport.toJSONString());
        }
    }

    public List<String> getRegistry(String id,ApplicationContext applicationContext){
       try {
           Registry registry = applicationContext.getBean(Registry.class);
           RedisApi.createJedisPool(registry.getAddress());
           if(RedisApi.exists(id)){
               //拿key对应的list
               return RedisApi.lrange(id);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
        return null;

    }

}
