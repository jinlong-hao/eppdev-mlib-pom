/* FileName: DimAreaInfoController.java
 * Copyright EPPDEV.CN, All Rights Preserved!
 * License: Anti-996 License 1.0
 * Auto created by eppdev-jee(http://jee.eppdev.cn)!
 */

package cn.eppdev.mlib.orm.reg.rest;

import cn.eppdev.mlib.orm.commons.rest.BasicController;
import cn.eppdev.mlib.orm.reg.entity.RegModelProviderMap;
import cn.eppdev.mlib.orm.reg.param.RegModelProviderMapParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinlong.hao
 */
@RestController
@RequestMapping(RegModelProviderMapController.URL)
public class RegModelProviderMapController extends BasicController<RegModelProviderMap, RegModelProviderMapParam> {
    static Logger logger = LoggerFactory.getLogger(RegModelProviderMapController.class);

    public static final String URL= "/reg_model_provider_map";

    @Override
    public String getBasicUrl() {
        return URL;
    }
}
