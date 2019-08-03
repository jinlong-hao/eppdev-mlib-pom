/* FileName: ConsumerTask.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License v1.0
 */

package cn.eppdev.mlib.consumer.task;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import cn.eppdev.mlib.commons.rest.RestResult;
import cn.eppdev.mlib.consumer.service.InfoService;
import cn.eppdev.mlib.consumer.service.ModelService;
import cn.eppdev.mlib.consumer.service.ProviderService;
import cn.eppdev.mlib.consumer.service.RegisterService;
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

import java.util.List;

/**
 * consumer的定制任务，主要包括： <br />
 * <ul>
 * <li>1. 定期发送心跳到register【15秒一次】</li>
 * <li>2. 注册失败时，每5分钟自动注册一次</li>
 * <li>3. 定期更新provider信息【9分钟一次】</li>
 * <li>4. 定期更新model的配置信息【11分钟一次】</li>
 * </ul>
 *
 * @author jinlong.hao
 */
@Component
@EnableAsync
@EnableScheduling
public class ConsumerTask {
    static Logger logger = LoggerFactory.getLogger(ConsumerTask.class);

    boolean registed = false;

    @Autowired
    RegisterService registerService;

    @Autowired
    InfoService infoService;

    @Autowired
    ProviderService providerService;

    @Autowired
    ModelService modelService;

    @Scheduled(fixedDelay = 1000L * 15)
    public void heartBeat() {
        EppdevMlibHeartBeatUtils.sendHeartBeat(registerService.getRegister(), infoService.getInstanceInfo());
    }


    @Async
    @Scheduled(fixedDelay = 1000 * 300)
    public void register() {
        if (!registed) {
            try {
                Thread.sleep(5 * 2000);
            } catch (InterruptedException e) {
                logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            }
            this.registed = EppdevMlibRegistUtils.register(registerService.getRegister(), infoService.getInstanceInfo());
            // 注册成功后，获取所有的provider信息
            if (this.registed) {
                updateProviders();
            }
        }
    }


    @Async
    @Scheduled(fixedDelay = 1000 * 9 * 60)
    public void updateProviders() {
        RestResult<List<EppdevMlibInstance>> restResult = EppdevMlibRegistUtils.listAllProviders(registerService.getRegister());
        if (restResult.getStatus() == RestResult.STATUS_SUCCESS) {
            providerService.updateProviders(restResult.getData());
        }
    }


    @Async
    @Scheduled(fixedDelay = 1000 * 11 * 60)
    public void updateAllModel() {
        modelService.refreshAllModel();
    }


}
