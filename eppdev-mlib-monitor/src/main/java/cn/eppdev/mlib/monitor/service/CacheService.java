/* FileName: CacheService.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License V1.0
 */

package cn.eppdev.mlib.monitor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用于缓存近期接口调用数量数据
 * @author jinlong.hao
 */
@Service
public class CacheService {
    static Logger logger = LoggerFactory.getLogger(CacheService.class);

    /**
     * 针对Provider的十分钟级缓存<br />
     * Key: 整数的时间
     * Value：具体的缓存数据
     */
    public static ConcurrentHashMap<Long, List<String>> PROVIDER_CACHE_MINUTES = new ConcurrentHashMap<>();

    /**
     * 针对Provider的小时级缓存
     */
    public static ConcurrentHashMap<Long, List<String>> PROVIDER_CACHE_HOURLY = new ConcurrentHashMap<>();

    /**
     * 针对Provider的分天的缓存
     */
    public static ConcurrentHashMap<Long, List<String>> PROVIDER_CACHE_DAILY = new ConcurrentHashMap<>();


    /**
     * 针对模型的十分钟级缓存
     */
    public static ConcurrentHashMap<Long, List<String>> MODEL_CACHE_MINUTES = new ConcurrentHashMap<>();

    /**
     * 针对模型的小时级缓存
     */
    public static ConcurrentHashMap<Long, List<String>> MODEL_CACHE_HOURLY = new ConcurrentHashMap<>();


    /**
     * 针对模型的分天的缓存
     */
    public static ConcurrentHashMap<Long, List<String>> MODEL_CACHE_DAILY = new ConcurrentHashMap<>();




}
