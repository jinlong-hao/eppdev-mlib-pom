/* FileName: BasicController.java
 * Copyright jinlong.hao(jinlong.hao@eppdev.cn).  All Rights Preserved!
 * Licensed By Anti-996 License
 */

package cn.eppdev.mlib.commons.rest;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import cn.eppdev.mlib.util.EppdevMlibHeartBeatUtils;
import cn.eppdev.mlib.util.EppdevMlibInstanceInfoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 基础的Controller，提供几个基础信息
 *
 * @author jinlong.hao
 */
public abstract class BasicController {
    static Logger logger = LoggerFactory.getLogger(BasicController.class);

    /**
     * 监测心跳信息，用来判断本服务是否正常服务中，返回值为：<br />
     * <ul>
     * <li>1. status: 状态，2000位状态，其余为有故障</li>
     * <li>2. message: 出错信息</li>
     * <li>3. data: 不需要</li>
     * </ul>
     *
     * @return 返回接口是否可用信息
     */
    @GetMapping(EppdevMlibHeartBeatUtils.CHECK_URL)
    public RestResult<Object> check() {
        return new RestResult<>(2000, "I'm alive!", null);
    }

    /**
     * 获取实例信息，用于进行信息的展示
     *
     * @return 实例信息
     */
    @GetMapping(EppdevMlibInstanceInfoUtils.INFO_URL)
    public abstract RestResult<EppdevMlibInstance> info();


}
