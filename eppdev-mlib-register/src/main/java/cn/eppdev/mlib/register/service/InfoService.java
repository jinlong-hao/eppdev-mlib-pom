/* FileName: InfoService.java
 * Copyright jinlong.hao(jinlong.hao@eppdev.cn).  All Rights Preserved!
 * Licensed By Anti-996 License
 */

package cn.eppdev.mlib.register.service;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import cn.eppdev.mlib.commons.service.BasicInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author jinlong.hao
 */
@Service
public class InfoService extends BasicInfoService {
    static Logger logger = LoggerFactory.getLogger(InfoService.class);

    private static EppdevMlibInstance MLIB_REGISTER_INFO = null;

    public EppdevMlibInstance getInstanceInfo(){
        if (MLIB_REGISTER_INFO == null) {
            MLIB_REGISTER_INFO = new EppdevMlibInstance();
            MLIB_REGISTER_INFO.setBasicUrl(getBasicUrl());
            MLIB_REGISTER_INFO.setInstanceName(getInstanceName());
        }
        return MLIB_REGISTER_INFO;
    }
}
