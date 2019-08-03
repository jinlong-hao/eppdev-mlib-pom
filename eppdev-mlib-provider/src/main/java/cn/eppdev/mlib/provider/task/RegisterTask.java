/* FileName: RegisterTask.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License v1.0
 */

package cn.eppdev.mlib.provider.task;

import cn.eppdev.mlib.provider.service.InfoService;
import cn.eppdev.mlib.provider.service.RegisterService;
import cn.eppdev.mlib.util.EppdevMlibHeartBeatUtils;
import cn.eppdev.mlib.util.EppdevMlibRegistUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author jinlong.hao
 */
@Component
@EnableAsync
@EnableScheduling
public class RegisterTask {
    static Logger logger = LoggerFactory.getLogger(RegisterTask.class);

    boolean registed = false;

    @Autowired
    RegisterService registerService;

    @Autowired
    InfoService infoService;

    /**
     * 定时发送心跳到Register，时间间隔为15秒一次
     */
    @Async
    @Scheduled(fixedDelay = 1000*15)
    public void heartbeat(){
        if (EppdevMlibHeartBeatUtils.sendHeartBeat(registerService.getRegister(), infoService.getInstanceInfo())) {
            logger.info("心跳发送成功！");
        } else {
            logger.warn("心跳发送失败！");
        }
    }

    @Async
    @Scheduled(fixedDelay = 1000*300)
    public void register() {
        if (!registed) {
            try {
                Thread.sleep(5*2000);
            } catch (InterruptedException e) {
                logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            }
            EppdevMlibRegistUtils.register(registerService.getRegister(), infoService.getInstanceInfo());
        }
    }
}
