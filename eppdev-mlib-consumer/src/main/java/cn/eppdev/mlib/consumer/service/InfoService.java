/* FileName: InfoService.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License v1.0
 */

package cn.eppdev.mlib.consumer.service;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author jinlong.hao
 */
@Service
public class InfoService {
    static Logger logger = LoggerFactory.getLogger(InfoService.class);

    EppdevMlibInstance instance = null;

    @Value("${eppdev.mlib.instance-name}")
    private String instanceName;

    @Value("${eppdev.mlib.basic-url}")
    private String basicUrl;

    @PostConstruct
    public void init(){
        instance = new EppdevMlibInstance();
        instance.setBasicUrl(basicUrl);
        instance.setInstanceName(instanceName);
        instance.setInstanceType(EppdevMlibInstance.INSTANCE_TYPE_CONSUMER);
        instance.setStatus(EppdevMlibInstance.STATUS_ALIVE);
        instance.setModelCnt(0);
    }

    /**
     * 获取consumer的信息
     * @return 获取consumer信息
     */
    public EppdevMlibInstance getInstanceInfo(){
        return instance;
    }

}
