/* FileName: ClientCacheService.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License v1.0
 */

package cn.eppdev.mlib.register.service;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jinlong.hao
 */
@Service
public class ClientCacheService {
    static Logger logger = LoggerFactory.getLogger(ClientCacheService.class);


    /**
     * eppdev-mlib-provider缓存信息
     */
    private static ConcurrentHashMap<String, EppdevMlibInstance> CACHED_PROVIDER_MAP = new ConcurrentHashMap<>();


    /**
     * eppdev-mlib-consumer缓存信息
     */
    private static ConcurrentHashMap<String, EppdevMlibInstance> CACHED_CONSUMER_MAP = new ConcurrentHashMap<>();

    /**
     * eppdev-mlib-monitor缓存信息
     */
    private static ConcurrentHashMap<String, EppdevMlibInstance> CACHED_MONITOR_MAP = new ConcurrentHashMap<>();

    /**
     * eppdev-mlib-register缓存信息
     */
    private static ConcurrentHashMap<String, EppdevMlibInstance> CACHED_REGISTER_MAP = new ConcurrentHashMap<>();

    /**
     * 模型的缓存信息
     */
    private static ConcurrentHashMap<String, List<EppdevMlibInstance>> CACHED_MODEL_MAP = new ConcurrentHashMap<>();


    public void cacheClient(EppdevMlibInstance instance) {
        instance.setLastHeartBeatTime(System.currentTimeMillis());
        String instanceType = instance.getInstanceType();
        if (instanceType != null) {
            if (instanceType.equals(EppdevMlibInstance.INSTANCE_TYPE_PROVIDER)) {
                CACHED_PROVIDER_MAP.put(instance.getInstanceName(), instance);
            } else if (instanceType.equals(EppdevMlibInstance.INSTANCE_TYPE_CONSUMER)) {
                CACHED_CONSUMER_MAP.put(instance.getInstanceName(), instance);
            } else if (instanceType.equals(EppdevMlibInstance.INSTANCE_TYPE_MONITOR)) {
                CACHED_MONITOR_MAP.put(instance.getInstanceName(), instance);
            } else if (instanceType.equals(EppdevMlibInstance.INSTANCE_TYPE_BACKUP_REGISTER) ||
                    instanceType.equals(EppdevMlibInstance.INSTANCE_TYPE_MAIN_REGISTER)) {
                CACHED_REGISTER_MAP.put(instance.getInstanceName(), instance);
            }
        }
    }


    /**
     * 获取所有的provider信息
     * @return 获取所有做的provider信息
     */
    public List<EppdevMlibInstance> listProviders(){
        List<EppdevMlibInstance> list = new ArrayList<>();
        list.addAll(CACHED_PROVIDER_MAP.values());
        return list;
    }


    /**
     * 获取所有的consumer信息
     * @return 所有的consumer列表信息
     */
    public List<EppdevMlibInstance> listConsumers() {
        List<EppdevMlibInstance> list = new ArrayList<>();
        list.addAll(CACHED_CONSUMER_MAP.values());
        return list;
    }


    /**
     * 获取所有的monitor列表信息
     * @return 所有的monitor信息列表
     */
    public List<EppdevMlibInstance> listMonitor() {
        List<EppdevMlibInstance> list = new ArrayList<>();
        list.addAll(CACHED_MONITOR_MAP.values());
        return list;
    }

    /**
     * 判断该provider是否已注册
     * @param instanceName provider的实例名称
     * @return provider是否已注册
     */
    public boolean hasProvider(String instanceName) {
        return CACHED_PROVIDER_MAP.containsKey(instanceName);
    }
}
