/* FileName: ProviderRestController.java
 * Copyright jinlong.hao(jinlong.hao@eppdev.cn).  All Rights Preserved!
 * Licensed By Anti-996 License
 */

package cn.eppdev.mlib.provider.web;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import cn.eppdev.mlib.commons.instance.EppdevMlibModel;
import cn.eppdev.mlib.commons.rest.BasicController;
import cn.eppdev.mlib.commons.rest.RestResult;
import cn.eppdev.mlib.provider.service.ExecutorService;
import cn.eppdev.mlib.provider.service.InfoService;
import cn.eppdev.mlib.provider.service.ProviderService;
import cn.eppdev.mlib.util.EppdevMlibProviderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * EPPDEV-MLIB-PROVIDER的微服务入口，提供的接口包括：<br />
 * <ul>
 *     <li>1. /check：心跳的检查接口【继承自BasicController】</li>
 *     <li>2. /info：获取实例信息，【继承自BasicController，本地实现】</li>
 *     <li>3. /model/{modelId}/deploy：模型发布接口</li>
 *     <li>4. /model/{modelId}/calc：模型计算接口</li>
 *     <li>5. /model/{modelId}/undeploy：模型卸载接口</li>
 *     <li>6. /model/list：当前部署的模型列表</li>
 *     <li>7. /model/{modelId}/status：判断是否有此模型</li>
 * </ul>
 * @author jinlong.hao
 */
@RestController
public class ProviderRestController extends BasicController {
    static Logger logger = LoggerFactory.getLogger(ProviderRestController.class);

    @Autowired
    InfoService infoService;

    @Autowired
    ProviderService providerService;

    @Autowired
    ExecutorService executorService;

    /**
     * 方法2：获取实例信息
     * @return 实例信息
     */
    //@RequestMapping("/info")
    @Override
    public RestResult<EppdevMlibInstance> info() {
        return new RestResult<>(RestResult.STATUS_SUCCESS, "返回成功", infoService.getInstanceInfo());
    }

    /**
     * 方法3：模型发布接口
     * @param modelInfo 模型信息
     * @return 是否部署成功
     */
    @RequestMapping(EppdevMlibProviderUtils.DEPLOY_URL)
    public RestResult<Integer> deployModel(@RequestBody EppdevMlibModel modelInfo){
        int cnt = providerService.deploy(modelInfo);
        return new RestResult<>(RestResult.STATUS_SUCCESS, "发布成功", cnt);
    }


    /**
     * 方法4：模型计算
     * @param modelId 模型id
     * @param content 模型变量属性
     * @return 计算结果
     */
    @RequestMapping("/model/{modelId}/calc")
    public RestResult<String> calcModel(@PathVariable("modelId") String modelId,
                                        @RequestBody String content){
        String result = providerService.execute(modelId, content);
        logger.debug("/model/{}/calc -> content: {},  result:{}", modelId, content, result);
        if (result != null ) {
            return new RestResult<>(RestResult.STATUS_SUCCESS, "计算成功", result);
        } else {
            return new RestResult<>(RestResult.STATUS_MODEL_NOT_FOUND, "模型不存在", null);
        }
    }


    /**
     * 方法5：模型卸载
     * @param modelId 模型id
     * @return 是否卸载成功
     */
    @RequestMapping(EppdevMlibProviderUtils.UNDEPLOY_URL)
    public RestResult<Object> undeployModel(@PathVariable("modelId") String modelId) {
        int i = providerService.undeploy(modelId);
        if (i == 1) {
            return new RestResult<>(RestResult.STATUS_SUCCESS, "模型卸载成功", i);
        } else {
            return new RestResult<>(RestResult.STATUS_INTERNEL_ERROR, "模型卸载不成功, 已卸载？", i);
        }
    }

    /**
     * 方法6：模型列表
     * @return 模型列表信息
     */
    @RequestMapping("/model/list")
    public RestResult<List<String>> listModel(){
        return new RestResult<>(RestResult.STATUS_SUCCESS, "接口成功", executorService.listModel());
    }


    /**
     * 方法7：模型状态，true表示正常，false表示模型不存在
     * @param modelId 模型id
     * @return 模型是否存在
     */
    @RequestMapping("/model/{modelId}/status")
    public RestResult<Boolean> modelStatus(@PathVariable("modelId") String modelId) {
       return new RestResult<>(RestResult.STATUS_SUCCESS, "接口成功", providerService.hasModel(modelId));
    }
}
