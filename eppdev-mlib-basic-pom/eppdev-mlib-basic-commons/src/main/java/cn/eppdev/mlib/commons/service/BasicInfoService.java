/* FileName: BasicInfoService.java
 * Copyright jinlong.hao(jinlong.hao@eppdev.cn).  All Rights Preserved!
 * Licensed By Anti-996 License
 */

package cn.eppdev.mlib.commons.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author jinlong.hao
 */
public class BasicInfoService {
    static Logger logger = LoggerFactory.getLogger(BasicInfoService.class);

    @Value("${eppdev.mlib.instance-name}")
    private String instanceName;

    @Value("${eppdev.mlib.basic-url}")
    private String basicUrl;

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getBasicUrl() {
        return basicUrl;
    }

    public void setBasicUrl(String basicUrl) {
        this.basicUrl = basicUrl;
    }
}
