/* FileName: MonitorRestController.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License V1.0
 */

package cn.eppdev.mlib.monitor.web;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import cn.eppdev.mlib.commons.rest.BasicController;
import cn.eppdev.mlib.commons.rest.RestResult;
import cn.eppdev.mlib.monitor.service.InfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EPPDEV-MLIB-MONITOR的微服务入口，提供的服务接口包括： <br />
 * <ul>
 *     <li>1. /check：检查是否或者，在BasicController中定义并实现</li>
 *     <li>2. /info：获取monitor实例信息，在BasicController中定义，本地实现</li>
 *     <li>3. /upload/{instanceName}/{dataType}：数据上报接口</li>
 *     <li>4. /log/provider/{pageNum}: 针对provider的报表</li>
 *     <li>5. /log/model/{pageNum}: 针对model的报表</li>
 *     <li>6. /history/provider/{instanceName}: 针对某个provider的历史记录</li>
 *     <li>7. /history/model/{modelId}：针对某个模型的历史记录</li>
 * </ul>
 * @author jinlong.hao
 */
@RestController
public class MonitorRestController extends BasicController {
    static Logger logger = LoggerFactory.getLogger(MonitorRestController.class);

    @Autowired
    InfoService infoService;

    @Override
    public RestResult<EppdevMlibInstance> info() {
        return new RestResult<>(RestResult.STATUS_SUCCESS, "获取成功", infoService.getInstanceInfo());
    }


    @RequestMapping("/upload/{instanceName}/{dataType}")
    public RestResult<String> upload(@PathVariable("instanceName") String instanceName,
                                     @PathVariable("dataType") Integer dataType,
                                     @RequestBody String content) {
        return null;
    }


    @RequestMapping("/log/provider/{pageNum}")
    public RestResult<String> logByProvider() {
        return null;
    }


    @RequestMapping("/log/model/{pageNum}")
    public RestResult<String> logByModel(){
        return null;
    }


    @RequestMapping("/history/provider/{intanceName}")
    public RestResult<String> historyByProvider() {
        return null;
    }


    @RequestMapping("/history/model/{modelId}")
    public RestResult<String> historyByModel() {
        return null;
    }

}
