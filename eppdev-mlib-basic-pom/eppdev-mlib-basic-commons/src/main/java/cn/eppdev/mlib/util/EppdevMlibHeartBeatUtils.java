/* FileName: EppdevMlibHeartBeatUtils.java
 * Copyright jinlong.hao(jinlong.hao@eppdev.cn).  All Rights Preserved!
 * Licensed By Anti-996 License
 */

package cn.eppdev.mlib.util;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import cn.eppdev.mlib.commons.rest.RestResult;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 心跳监测接口信息
 * @author jinlong.hao
 */
public class EppdevMlibHeartBeatUtils {
    static Logger logger = LoggerFactory.getLogger(EppdevMlibHeartBeatUtils.class);

    public static final String CHECK_URL = "/check";

    public static final String HEARTBEAT_URL = "/heartbeat";

    /**
     * 检查心跳，确认该实例是否还处于工作状态
     * @param instance 要检查的实例信息
     * @return 检查是否成功
     */
    public static boolean isAlive(EppdevMlibInstance instance) {
        String resultContent = EppdevMlibHttpUtils.get(instance, CHECK_URL);
        if (resultContent == null) {
            return false;
        } else {
            RestResult<String> restResult = EppdevMlibJsonUtils.readValue(resultContent,
                    new TypeReference<RestResult<String>>() {
            });
            if (restResult == null) {
                return false;
            } else {
                return RestResult.STATUS_SUCCESS == restResult.getStatus();
            }
        }
    }


    /**
     * 向register发送心跳信息，告诉register我还活着
     * @param register 要接收心跳的register
     * @param instance 心跳发送者信息
     * @return 是否发送成功
     */
    public static boolean sendHeartBeat(EppdevMlibInstance register, EppdevMlibInstance instance){
        String resultContent = EppdevMlibHttpUtils.post(register, HEARTBEAT_URL, instance.toString());
        if (resultContent == null) {
            return false;
        } else {
            RestResult<String> restResult = EppdevMlibJsonUtils.readValue(resultContent,
                    new TypeReference<RestResult<String>>() {
                    });
            if (restResult == null) {
                return false;
            } else {
                return RestResult.STATUS_SUCCESS == restResult.getStatus();
            }
        }
    }

}
