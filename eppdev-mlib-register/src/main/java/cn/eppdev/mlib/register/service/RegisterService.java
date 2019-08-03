/* FileName: RegisterService.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License v1.0
 */

package cn.eppdev.mlib.register.service;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jinlong.hao
 */
@Service
@EnableAsync
public class RegisterService {
    static Logger logger = LoggerFactory.getLogger(RegisterService.class);


    @Autowired
    ProviderService providerService;


    @Autowired
    ModelService modelService;

    @Autowired
    ClientService clientService;


    /**
     * 处理心跳信息
     *
     * @param instance
     */
    public void heartBeat(EppdevMlibInstance instance) {
        clientService.heartBeat(instance);
    }

    /**
     * 处理客户端的注册信息
     * @param instance 要注册的客户端
     */
    public void regist(EppdevMlibInstance instance) {
        clientService.regist(instance);
    }



    /**
     * 获取所有的provider信息
     * @return 获取所有做的provider信息
     */
    public List<EppdevMlibInstance> listProviders(){
        List<EppdevMlibInstance> list = clientService.listProviders();
        for (EppdevMlibInstance instance: list) {
            if (System.currentTimeMillis() - instance.getLastHeartBeatTime() > 20 * 1000l) {
                instance.setStatus(EppdevMlibInstance.STATUS_DEAD);
            }
        }
        return list;
    }


    /**
     * 获取所有的consumer信息
     * @return 所有的consumer列表信息
     */
    public List<EppdevMlibInstance> listConsumers() {
        List<EppdevMlibInstance> list = clientService.listConsumers();
        for (EppdevMlibInstance instance: list) {
            if (System.currentTimeMillis() - instance.getLastHeartBeatTime() > 20 * 1000l) {
                instance.setStatus(EppdevMlibInstance.STATUS_DEAD);
            }
        }
        return list;
    }


    /**
     * 获取所有的monitor列表信息
     * @return 所有的monitor信息列表
     */
    public List<EppdevMlibInstance> listMonitor() {
        List<EppdevMlibInstance> list = clientService.listMonitor();
        for (EppdevMlibInstance instance: list) {
            if (System.currentTimeMillis() - instance.getLastHeartBeatTime() > 20 * 1000l) {
                instance.setStatus(EppdevMlibInstance.STATUS_DEAD);
            }
        }
        return list;
    }

}
