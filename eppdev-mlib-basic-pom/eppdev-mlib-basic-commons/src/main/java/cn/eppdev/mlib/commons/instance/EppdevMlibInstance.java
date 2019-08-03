/* FileName: EppdevMlibInstance.java
 * Copyright jinlong.hao(jinlong.hao@eppdev.cn).  All Rights Preserved!
 * Licensed By Anti-996 License
 */

package cn.eppdev.mlib.commons.instance;

import cn.eppdev.mlib.util.EppdevMlibJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jinlong.hao
 */
public class EppdevMlibInstance {
    static Logger logger = LoggerFactory.getLogger(EppdevMlibInstance.class);

    public static final String INSTANCE_TYPE_PROVIDER = "provider";
    public static final String INSTANCE_TYPE_CONSUMER = "consumer";
    public static final String INSTANCE_TYPE_MONITOR = "monitor";
    public static final String INSTANCE_TYPE_MAIN_REGISTER = "main-register";
    public static final String INSTANCE_TYPE_BACKUP_REGISTER = "backup-register";


    /**
     * 实例状态（正常状态）
     */
    public static final int STATUS_ALIVE = 1;
    /**
     * 实例状态（下线状态）
     */
    public static final int STATUS_DEAD = 0;


    /**
     * 启动的基础url信息，如： http://localhost:11531/
     */
    private String basicUrl;

    /**
     * 实例名称，如Provider01
     */
    private String instanceName;

    /**
     * 实例状态
     */
    private int status;

    /**
     * 最后的心跳时间
     */
    private long lastHeartBeatTime;

    /**
     * 实例的类型，具体包括, main-register, backup-register, provider, consumer, monitor
     */
    private String instanceType;


    /**
     * 已部署的模型数量
     */
    private int modelCnt;


    /**
     * 获取实例类型，具体包括：register, backup-register, provider, consumer, monitor
     *
     * @return 具体的实例状态
     */
    public String getInstanceType() {
        return this.instanceType;
    }

    /**
     * 设置实例类型信息
     *
     * @param instanceType 实例类型，具体包括：register, backup-register, provider, consumer, monitor
     */
    public void setInstanceType(String instanceType){
        this.instanceType = instanceType;
    }

    public String getBasicUrl() {
        return basicUrl;
    }

    public void setBasicUrl(String basicUrl) {
        this.basicUrl = basicUrl;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getLastHeartBeatTime() {
        return lastHeartBeatTime;
    }

    public void setLastHeartBeatTime(long lastHeartBeatTime) {
        this.lastHeartBeatTime = lastHeartBeatTime;
    }

    public int getModelCnt() {
        return modelCnt;
    }

    public void setModelCnt(int modelCnt) {
        this.modelCnt = modelCnt;
    }

    @Override
    public String toString() {
        return EppdevMlibJsonUtils.toJson(this);
    }
}
