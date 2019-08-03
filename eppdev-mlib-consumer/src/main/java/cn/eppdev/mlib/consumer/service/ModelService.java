/* FileName: ModelService.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License v1.0
 */

package cn.eppdev.mlib.consumer.service;

import cn.eppdev.mlib.commons.rest.RestResult;
import cn.eppdev.mlib.util.EppdevMlibRegistUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jinlong.hao
 */
@Service
@EnableAsync
public class ModelService {
    static Logger logger = LoggerFactory.getLogger(ModelService.class);

    public static ConcurrentHashMap<String, Set<String>> MODEL_MAP = new ConcurrentHashMap<>();

    @Autowired
    RegisterService registerService;

    public Set<String> getProvidersByModelId(String modelId) {
        if (MODEL_MAP.containsKey(modelId)) {
            return MODEL_MAP.get(modelId);
        } else {
            RestResult<Set<String>> restResult = EppdevMlibRegistUtils.listProvidersByModel(registerService.getRegister(), modelId);
            if (RestResult.STATUS_SUCCESS == restResult.getStatus()) {
                MODEL_MAP.put(modelId, restResult.getData());
                return restResult.getData();
            } else {
                return new HashSet<>();
            }
        }
    }


    /**
     * 刷新所有的模型信息
     */
    @Async
    public void refreshAllModel() {
        for (String modelId : MODEL_MAP.keySet()) {
            RestResult<Set<String>> restResult = EppdevMlibRegistUtils.listProvidersByModel(registerService.getRegister(), modelId);
            if (RestResult.STATUS_SUCCESS == restResult.getStatus() && restResult.getData().size() > 0) {
                MODEL_MAP.put(modelId, restResult.getData());
            } else {
                logger.warn("更新模型所在provider信息错误，找不到：{}", modelId);
            }
        }
    }
}
