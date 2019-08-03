/* FileName: ClientService.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License v1.0
 */

package cn.eppdev.mlib.register.service;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import cn.eppdev.mlib.commons.instance.EppdevMlibModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jinlong.hao
 */
@Service
@EnableAsync
public class ClientService {
    static Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    ClientCacheService cacheService;


    @Autowired
    ProviderService providerService;


    @Autowired
    ModelService modelService;

    /**
     * 处理心跳信息，具体逻辑：<br />
     * <ul>
     *     <li>1. 如果已有注册信息，则更新注册信息即可</li>
     *     <li>2. 如无注册信息（一般是register挂掉以后重启），则需要获取provider已有的model信息，更新model信息</li>
     * </ul>
     *
     * @param instance
     */
    public void heartBeat(EppdevMlibInstance instance) {
        if (EppdevMlibInstance.INSTANCE_TYPE_PROVIDER.equals(instance.getInstanceType())
                && !cacheService.hasProvider(instance.getInstanceName())) {
            // TODO
            // 获取已经部署的model，更新到服务器上
        }
        cacheService.cacheClient(instance);
    }


    /**
     * 处理客户端的注册信息
     * @param instance 要注册的客户端
     */
    public void regist(EppdevMlibInstance instance) {
        cacheService.cacheClient(instance);
        if (instance.getInstanceType() != null &&
                instance.getInstanceType().equals(EppdevMlibInstance.INSTANCE_TYPE_PROVIDER)) {
            providerService.deployModel(instance, modelService.listConfigModelByProvider(instance.getInstanceName()));
        }
    }


    /**
     * 获取所有的provider信息
     * @return 获取所有做的provider信息
     */
    public List<EppdevMlibInstance> listProviders(){
        return cacheService.listProviders();
    }


    /**
     * 获取所有的consumer信息
     * @return 所有的consumer列表信息
     */
    public List<EppdevMlibInstance> listConsumers() {
        return cacheService.listConsumers();
    }


    /**
     * 获取所有的monitor列表信息
     * @return 所有的monitor信息列表
     */
    public List<EppdevMlibInstance> listMonitor() {
        return cacheService.listMonitor();
    }

}
