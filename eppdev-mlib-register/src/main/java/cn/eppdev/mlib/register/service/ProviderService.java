/* FileName: ProviderService.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License v1.0
 */

package cn.eppdev.mlib.register.service;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import cn.eppdev.mlib.commons.instance.EppdevMlibModel;
import cn.eppdev.mlib.commons.rest.RestResult;
import cn.eppdev.mlib.util.EppdevMlibProviderUtils;
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
public class ProviderService {
    static Logger logger = LoggerFactory.getLogger(ProviderService.class);

    @Autowired
    ModelCacheService modelService;

    /**
     * 异步的批量模型部署方法，用于provider注册后的模型自动部署 <br />
     * <ul>
     *     <li>1. 调用Thread.sleep方法，等待5秒钟，待provider启动完毕</li>
     *     <li>2. 一次调用单个模型的发布接口，完成模型的发布，并进行成功数量的计数</li>
     *     <li>3. 返回成功的部署的模型数量</li>
     * </ul>
     * @param provider 要部署的provider
     * @param modelList 要部署的模型列表
     * @return 部署成功的模型数量
     */
    @Async
    public void  deployModel(EppdevMlibInstance provider, List<EppdevMlibModel> modelList) {
        try {
            logger.info("等待5秒后，发布模型");
            Thread.sleep(1000L * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("deploy model for: {}", provider.getInstanceName());
        for (EppdevMlibModel model : modelList) {
            deployModel(provider, model);
        }
    }


    /**
     * 单个模型的部署，调用provider接口，完成模型的部署，具体逻辑：<br />
     * <ul>
     *     <li>1. 调用provider的deploy接口，完成模型在provider中的部署</li>
     *     <li>2. 调用ModelService中cache方法，完成模型信息的缓存</li>
     * </ul>
     * @param provider 要部署model的provider
     * @param model 要部署的模型信息
     * @return 1表示部署成功，0表示部署失败
     */
    public int deployModel(EppdevMlibInstance provider, EppdevMlibModel model) {
        RestResult<String> restResult = EppdevMlibProviderUtils.deploy(provider, model);
        if (restResult.getStatus() == RestResult.STATUS_SUCCESS) {
            modelService.cacheModel(model.getModelId(), provider.getInstanceName());
            logger.info("模型（{}）成功部署于（{}）", model.getModelId(), provider.getInstanceName());
            return 1;
        } else {
            logger.warn("模型（{}）在（{}）部署失败", model.getModelId(), provider.getInstanceName());
            return 0;
        }
    }

    /**
     * 将一个模型从某个provider中就进行卸载
     * @param provider 要卸载模型的provider
     * @param modelId 模型id
     * @return 卸载模型的数量
     */
    public int undeployModel(EppdevMlibInstance provider, String modelId) {
        RestResult<String> restResult = EppdevMlibProviderUtils.undeploy(provider, modelId);
        if (restResult.getStatus() == RestResult.STATUS_SUCCESS) {
            modelService.uncacheModel(modelId, provider.getInstanceName());
            logger.info("模型（{}）在（{}）成功卸载", modelId, provider.getInstanceName());
            return 1;
        } else {
            logger.warn("模型（{}）在（{}）卸载失败", modelId, provider.getInstanceName());
            return 0;
        }
    }


}
