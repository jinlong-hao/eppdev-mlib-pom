/* FileName: ProviderService.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License v1.0
 */

package cn.eppdev.mlib.consumer.service;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import cn.eppdev.mlib.util.EppdevMlibHeartBeatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用于缓存provider信息
 * @author jinlong.hao
 */
@Service
public class ProviderService {
    static Logger logger = LoggerFactory.getLogger(ProviderService.class);

    public static ConcurrentHashMap<String, EppdevMlibInstance> PROVIDER_MAP = new ConcurrentHashMap<>();

    @Autowired
    ModelService modelService;

    /**
     * 更新provider缓存信息
     * @param providers 要更新的provider信息
     */
    public void updateProviders(List<EppdevMlibInstance> providers) {
        // 一、判断已经缓存的provider中是否有在新的列表中不存在的provider，是则删除
        for (String instanceName: PROVIDER_MAP.keySet()) {
            boolean contains = false;
            for (EppdevMlibInstance instance: providers) {
                if (instance.getInstanceName().equals(instanceName)) {
                    contains = true;
                }
            }
            if (!contains) {
                PROVIDER_MAP.remove(instanceName);
            }
        }

        // 二、采用最新的provider，更新原有的provider
        for (EppdevMlibInstance instance : providers) {
            PROVIDER_MAP.put(instance.getInstanceName(), instance);
        }
    }

    /**
     * 根据provider的instanceName，获取provider信息
     * @param instanceName provider的instanceName
     * @return 对应的provider信息
     */
    public EppdevMlibInstance getProvider(String instanceName) {
        return PROVIDER_MAP.get(instanceName);
    }

    /**
     * 根据modelId，随机获取一个provider，用于进行调用，具体逻辑：<br />
     * <ul>
     *     <li>1. 首先获取所有已经部署了该模型的provider名称列表</li>
     *     <li>2. 随机获取一个provider，并验证此provider是否正常，正常则直接返回，重试5次</li>
     *     <li>3. 若随机不成功，则一次查看相应的模型，看是否成功</li>
     * </ul>
     * @param modelId 模型id
     * @return 一个可用的provider
     */
    public EppdevMlibInstance getRandomProviderByModelId(String modelId) {
        Set<String> set = modelService.getProvidersByModelId(modelId);
        Object[] providerNames = set.toArray();
        for (int j = 0; j <= 5; j++) {
            Random random = new Random();
            int i = random.nextInt(20000) % providerNames.length;
            String providerName = providerNames[i].toString();
            EppdevMlibInstance provider = PROVIDER_MAP.get(providerName);
            if (EppdevMlibHeartBeatUtils.isAlive(provider)) {
                return provider;
            }
        }

        for (Object obj: providerNames) {
            String providerName = obj.toString();
            EppdevMlibInstance provider = PROVIDER_MAP.get(providerName);
            if (EppdevMlibHeartBeatUtils.isAlive(provider)) {
                return provider;
            }
        }

        return null;
    }

}
