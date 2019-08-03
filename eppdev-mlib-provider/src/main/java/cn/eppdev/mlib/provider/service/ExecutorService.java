/* FileName: ExecutorService.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License v1.0
 */

package cn.eppdev.mlib.provider.service;

import cn.eppdev.mlib.commons.instance.EppdevMlibModel;
import cn.eppdev.mlib.commons.rest.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jinlong.hao
 */
@Service
public class ExecutorService {
    static Logger logger = LoggerFactory.getLogger(ExecutorService.class);

    @Autowired
    InfoService infoService;

    public static ConcurrentHashMap<String, String> CACHED_MODEL = new ConcurrentHashMap<>();

    public void deploy(EppdevMlibModel modelInfo) {
        CACHED_MODEL.put(modelInfo.getModelId(), modelInfo.getModelContent());
    }

    public int undeploy(String modelId) {
        if (CACHED_MODEL.containsKey(modelId)) {
            CACHED_MODEL.remove(modelId);
            return 1;
        }
        return 0;
    }

    public int countModel() {
        return  CACHED_MODEL.keySet().size();
    }

    public String execute(String modelId, String content) {
        if (CACHED_MODEL.containsKey(modelId)) {
            return CACHED_MODEL.get(modelId) + "\n" + content + "\n" + infoService.getInstanceInfo().toString();
        }
        return null;
    }

    public List<String> listModel(){
        List<String> list = new ArrayList<String>();
        list.addAll(CACHED_MODEL.keySet());
        return list;
    }

    public boolean hasModel(String modelId){
        return CACHED_MODEL.containsKey(modelId);
    }

}
