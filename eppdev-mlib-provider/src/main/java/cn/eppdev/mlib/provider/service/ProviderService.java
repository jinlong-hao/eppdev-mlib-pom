/* FileName: ProviderService.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License v1.0
 */

package cn.eppdev.mlib.provider.service;

import cn.eppdev.mlib.commons.instance.EppdevMlibModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jinlong.hao
 */
@Service
public class ProviderService {
    static Logger logger = LoggerFactory.getLogger(ProviderService.class);

    @Autowired
    ExecutorService executorService;

    public int deploy(EppdevMlibModel modelInfo) {
        executorService.deploy(modelInfo);
        return executorService.countModel();
    }

    public String execute(String modelId, String content) {
        return executorService.execute(modelId, content);
    }


    public int undeploy(String modelId) {
        return executorService.undeploy(modelId);
    }


    public boolean hasModel(String modelId) {
        return executorService.hasModel(modelId);
    }
}
