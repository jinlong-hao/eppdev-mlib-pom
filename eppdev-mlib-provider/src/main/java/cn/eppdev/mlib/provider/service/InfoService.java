/* FileName: InfoService.java
 * Copyright jinlong.hao(jinlong.hao@eppdev.cn).  All Rights Preserved!
 * Licensed By Anti-996 License
 */

package cn.eppdev.mlib.provider.service;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author jinlong.hao
 */
@Service
public class InfoService {
    static Logger logger = LoggerFactory.getLogger(InfoService.class);

    @Autowired
    ExecutorService executorService;

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
        instance.setInstanceType(EppdevMlibInstance.INSTANCE_TYPE_PROVIDER);
        instance.setStatus(EppdevMlibInstance.STATUS_ALIVE);
    }

    /**
     * 获取provider的信息
     * @return 获取provider信息
     */
    public EppdevMlibInstance getInstanceInfo(){
        instance.setModelCnt(executorService.countModel());
        return instance;
    }

}
