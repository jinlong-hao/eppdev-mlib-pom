/* FileName: ModelService.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License v1.0
 */

package cn.eppdev.mlib.register.service;

import cn.eppdev.mlib.commons.instance.EppdevMlibModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author jinlong.hao
 */
@Service
public class ModelService {
    static Logger logger = LoggerFactory.getLogger(ModelService.class);

    @Autowired
    ModelCacheService modelCacheService;

    public List<EppdevMlibModel> listConfigModelByProvider(String instanceName) {
        List<EppdevMlibModel> list = new ArrayList<>();
        if (instanceName.equals("provider1")) {
            list.add(new EppdevMlibModel("model1", "type1", "content1", "remark1"));
            list.add(new EppdevMlibModel("model2", "type2", "content2", "remark2"));
            list.add(new EppdevMlibModel("model3", "type3", "content3", "remark3"));
            list.add(new EppdevMlibModel("model4", "type4", "content4", "remark4"));
        } else if (instanceName.equals("provider2")) {
            list.add(new EppdevMlibModel("model2", "type2", "content2", "remark2"));
            list.add(new EppdevMlibModel("model3", "type3", "content3", "remark3"));
            list.add(new EppdevMlibModel("model5", "type5", "content5", "remark5"));
        }
        return list;
    }

    /**
     * 根据模型id，获取已部署了该模型的provider的instanceName列表
     * @param modelId 模型id
     * @return
     */
    public Set<String> listProviderByModelId(String modelId) {
        return modelCacheService.listProvidersByModel(modelId);
    }

}
