/* FileName: ExecutorService.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License v1.0
 */

package cn.eppdev.mlib.consumer.service;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import cn.eppdev.mlib.commons.rest.RestResult;
import cn.eppdev.mlib.util.EppdevMlibProviderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jinlong.hao
 */
@Service
public class ExecutorService {
    static Logger logger = LoggerFactory.getLogger(ExecutorService.class);

    @Autowired
    ProviderService providerService;

    public String calc(String modelId, String content) {
        // TODO 需处理provider中未部署该模型的问题
        EppdevMlibInstance provider = providerService.getRandomProviderByModelId(modelId);
        if (provider != null) {
            RestResult<String> restResult = EppdevMlibProviderUtils.execute(provider, modelId, content);
            if (restResult.getStatus() == RestResult.STATUS_SUCCESS) {
                return restResult.getData();
            }
        }
        return null;
    }
}
