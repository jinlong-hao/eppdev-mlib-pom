/* FileName: RegisterService.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License v1.0
 */

package cn.eppdev.mlib.provider.service;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import cn.eppdev.mlib.commons.rest.RestResult;
import cn.eppdev.mlib.util.EppdevMlibHeartBeatUtils;
import cn.eppdev.mlib.util.EppdevMlibInstanceInfoUtils;
import cn.eppdev.mlib.util.EppdevMlibRegistUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author jinlong.hao
 */
@Service
public class RegisterService {
    static Logger logger = LoggerFactory.getLogger(RegisterService.class);

    @Autowired
    InfoService infoService;

    @Value("${eppdev.mlib.register.main-register.basic-url}")
    private String mainRegisterBasicUrl;

    @Value("${eppdev.mlib.register.backup-register.basic-url:#{null}}")
    private String backupRegisterBasicUrl;


    private EppdevMlibInstance mainRegister = null;

    private EppdevMlibInstance backupRegister = null;

    @PostConstruct
    public void register(){
        initRegister();
        if (mainRegister != null && EppdevMlibHeartBeatUtils.isAlive(mainRegister)){
            EppdevMlibRegistUtils.register(mainRegister, infoService.getInstanceInfo());
        } else if (backupRegister != null && EppdevMlibHeartBeatUtils.isAlive(backupRegister)) {
            EppdevMlibRegistUtils.register(backupRegister, infoService.getInstanceInfo());
        }
    }

    private void initRegister(){
        logger.info("初始化register");
        if (mainRegister == null) {
            RestResult<EppdevMlibInstance> restResult = EppdevMlibInstanceInfoUtils.info(mainRegisterBasicUrl);
            logger.debug("获取main-register信息，restResult:{}", restResult);
            if (RestResult.STATUS_SUCCESS == restResult.getStatus()) {
                mainRegister = restResult.getData();
                logger.info("main register获取成功");
            }
        }

        if (backupRegister == null && backupRegisterBasicUrl != null) {
            RestResult<EppdevMlibInstance> restResult = EppdevMlibInstanceInfoUtils.info(backupRegisterBasicUrl);
            logger.debug("获取backup-register信息，restResult:{}", restResult);
            if (restResult.getStatus() == RestResult.STATUS_SUCCESS) {
                backupRegister = restResult.getData();
                logger.info("backup register信息获取成功");
            }
        }
    }

    public EppdevMlibInstance getRegister(){
        EppdevMlibInstance register = null;
        if (mainRegister != null && EppdevMlibHeartBeatUtils.isAlive(mainRegister)) {
            logger.debug("main register可用");
            register = mainRegister;
        } else if (backupRegister != null && EppdevMlibHeartBeatUtils.isAlive(backupRegister)) {
            logger.debug("backup register可用");
            register = backupRegister;
        }
        logger.debug("获取register信息，register:{}", register);
        return register;
    }
}
