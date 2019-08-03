/* FileName: RegisterRestController.java
 * Copyright jinlong.hao(jinlong.hao@eppdev.cn).  All Rights Preserved!
 * Licensed By Anti-996 License
 */

package cn.eppdev.mlib.register.web;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import cn.eppdev.mlib.commons.rest.BasicController;
import cn.eppdev.mlib.commons.rest.RestResult;
import cn.eppdev.mlib.register.service.ClientService;
import cn.eppdev.mlib.register.service.InfoService;
import cn.eppdev.mlib.register.service.ModelCacheService;
import cn.eppdev.mlib.register.service.ModelService;
import cn.eppdev.mlib.util.EppdevMlibHeartBeatUtils;
import cn.eppdev.mlib.util.EppdevMlibRegistUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * EPPDEV-MLIB-REGISTER的微服务的入口类，具体提供的方法包括：<br />
 * <ul>
 *     <li>1. /check： 可用性检查接口，继承自BasicController</li>
 *     <li>2. /info: 服务的基础信息，继承自BasicController，需要本地实现</li>
 *     <li>3. /heartbeat: 用于接所有的client的心跳信息</li>
 *     <li>4. /register: 用于服务的注册</li>
 *     <li>5. /provider/list: 获取所有的provider信息</li>
 *     <li>6. /consumer/list：获取获取的consumer信息</li>
 *     <li>7. /monitor/list：获取所有的monitor信息</li>
 *     <li>8. /model/all：获取所有的模型列表</li>
 *     <li>9. /model/{modelId}/providers：获取该模型的在用的provider列表</li>
 *     <li>10. /model/sync: 模型信息同步接口</li>
 * </ul>
 * @author jinlong.hao
 */
@RestController
@EnableAsync
public class RegisterRestController extends BasicController {
    static Logger logger = LoggerFactory.getLogger(RegisterRestController.class);

    @Autowired
    InfoService infoService;

    @Autowired
    ClientService clientService;

    @Autowired
    ModelService modelService;

    /**
     * 接口2：获取本实例的基础信息
     * @return 本示例的基础信息
     */
    @Override
    public RestResult<EppdevMlibInstance> info() {
        return new RestResult<>(RestResult.STATUS_SUCCESS, "返回成功", infoService.getInstanceInfo());
    }

    /**
     * 接口3：用于接收所有外部接口的基础信息
     * @param instance
     * @return
     */
    @RequestMapping(EppdevMlibHeartBeatUtils.HEARTBEAT_URL)
    public RestResult<Object> heartbeat(@RequestBody EppdevMlibInstance instance){
        try {
            clientService.heartBeat(instance);
            logger.info("{}: 心跳接收成功", instance.getInstanceName());
            return new RestResult<>(RestResult.STATUS_SUCCESS, "心跳接收成功", null);
        } catch (Exception e) {
            logger.error("{}: 心跳接收错误！Error: {}\n{}", instance.getInstanceName(),
                    e.getMessage(), e.getStackTrace());
            return new RestResult<>(RestResult.STATUS_INTERNEL_ERROR, e.getMessage(), null);
        }
    }

    /**
     * 接口4：用户所有服务的注册
     * @param instance
     * @return
     */
    @RequestMapping(EppdevMlibRegistUtils.REGISTER_URL)
    public RestResult<Object> regist(@RequestBody EppdevMlibInstance instance){
        try {
            clientService.regist(instance);
            logger.info("{}: 注册成功", instance.getInstanceName());
            return new RestResult<>(RestResult.STATUS_SUCCESS, "注册成功", null);
        } catch (Exception e) {
            logger.error("{}: 注册失败: {}\n{}", instance.getInstanceName(),
                    e.getMessage(), e.getStackTrace());
            return new RestResult<>(RestResult.STATUS_INTERNEL_ERROR, e.getMessage(), null);
        }
    }

    /**
     * 接口5：用于获取provider列表
     * @return 所有的provider列表
     */
    @RequestMapping(EppdevMlibRegistUtils.PROVIDER_LIST_URL)
    public RestResult<List<EppdevMlibInstance>> listAllProvider(){
        return new RestResult<>(RestResult.STATUS_SUCCESS, "获取成功", clientService.listProviders());
    }

    /**
     * 接口6：获取所有的consumer信息
     * @return 所有的consumer列表
     */
    @RequestMapping("/consumer/list")
    public RestResult<List<EppdevMlibInstance>> listConsumer(){
        return new RestResult<>(RestResult.STATUS_SUCCESS, "获取成功", clientService.listConsumers());
    }


    /**
     * 接口7：获取所有的monitor信息
     * @return 所有的monitor列表
     */
    @RequestMapping("/monitor/list")
    public RestResult<List<EppdevMlibInstance>> listMonitor(){
        return new RestResult<>(RestResult.STATUS_SUCCESS, "获取成功", clientService.listMonitor());
    }


    /**
     * 接口8： 获取所有模型信息
     * @return 所有的模型信息
     */
    @RequestMapping("/model/all")
    public RestResult<Map<String, Set<String>>> listModel() {
        return new RestResult<>(RestResult.STATUS_SUCCESS, "获取成功", ModelCacheService.CACHED_MODEL_MAP);
    }


    /**
     * 接口9：获取单个模型的信息
     * @param modelId  模型id
     * @return 模型信息
     */
    @RequestMapping(EppdevMlibRegistUtils.MODEL_PROVIDER_URL)
    public RestResult<Set<String>> listProviderByModelId(@PathVariable("modelId") String modelId){
        Set<String> set = modelService.listProviderByModelId(modelId);
        if (set != null && set.size() > 0) {
            return new RestResult<>(RestResult.STATUS_SUCCESS, "获取成功", set);
        }
        return new RestResult<>(RestResult.STATUS_MODEL_NOT_FOUND, "未部署", null);
    }

    /**
     * 接口10: 模型信息同步接口
     * @param content
     * @return
     */
    @RequestMapping("/model/sync")
    public RestResult<String> syncModel(@RequestBody String content){
        return null;
    }

}
