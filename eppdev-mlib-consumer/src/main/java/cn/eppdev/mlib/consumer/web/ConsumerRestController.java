/* FileName: ConsumerRestController.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License v1.0
 */

package cn.eppdev.mlib.consumer.web;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import cn.eppdev.mlib.commons.rest.BasicController;
import cn.eppdev.mlib.commons.rest.RestResult;
import cn.eppdev.mlib.consumer.service.ExecutorService;
import cn.eppdev.mlib.consumer.service.InfoService;
import cn.eppdev.mlib.consumer.service.ProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * EPPDEV-MLIB-CONSUMER提供的微服务入口，具体的接口包括：<br />
 * <ul>
 *     <li>1. /check：心跳的检查接口【继承自BasicController】</li>
 *     <li>2. /info：获取实例信息，【继承自BasicController，本地实现】</li>
 *     <li>3. /calc/{modelId}：模型计算</li>
 * </ul>
 * @author jinlong.hao
 */
@RestController
public class ConsumerRestController extends BasicController {
    static Logger logger = LoggerFactory.getLogger(ConsumerRestController.class);

    @Autowired
    InfoService infoService;

    @Autowired
    ExecutorService executorService;

    @Autowired
    ProviderService providerService;

    @Override
    public RestResult<EppdevMlibInstance> info() {
        return new RestResult<>(RestResult.STATUS_SUCCESS, "获取成功", infoService.getInstanceInfo());
    }


    @RequestMapping("/calc/{modelId}")
    public RestResult<String> calc(@PathVariable("modelId") String modelId,
                                   @RequestBody String content) {
        return new RestResult<>(RestResult.STATUS_SUCCESS, "计算成功", executorService.calc(modelId,content));
    }

    @RequestMapping("/provider/update")
    public RestResult<Object> udpateProvider(@RequestBody List<EppdevMlibInstance> providers) {
        providerService.updateProviders(providers);
        return new RestResult<>(RestResult.STATUS_SUCCESS, "更新成功", null);
    }

}
