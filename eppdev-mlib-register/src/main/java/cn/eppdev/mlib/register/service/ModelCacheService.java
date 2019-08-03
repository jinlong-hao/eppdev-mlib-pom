/* FileName: ModelCacheService.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License v1.0
 */

package cn.eppdev.mlib.register.service;

import cn.eppdev.mlib.commons.instance.EppdevMlibModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jinlong.hao
 */
@Service
public class ModelCacheService {
    static Logger logger = LoggerFactory.getLogger(ModelCacheService.class);

    public static ConcurrentHashMap<String, Set<String>> CACHED_MODEL_MAP = new ConcurrentHashMap<>();

    public void cacheModel(String modelId, String providerInstanceName) {
        if (!CACHED_MODEL_MAP.containsKey(modelId)) {
            Set<String> set = new HashSet<>();
            CACHED_MODEL_MAP.put(modelId, set);
        }
        CACHED_MODEL_MAP.get(modelId).add(providerInstanceName);
    }

    public void uncacheModel(String modelId, String providerInstance) {
        if (CACHED_MODEL_MAP.containsKey(modelId)) {
            Set<String> set = CACHED_MODEL_MAP.get(modelId);
            set.remove(providerInstance);
        }
    }

    public Set<String> listProvidersByModel(String modelId) {
        return CACHED_MODEL_MAP.get(modelId);
    }
}
