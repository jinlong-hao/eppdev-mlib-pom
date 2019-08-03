/* FileName: RegModelProviderMapService.java
 * Copyright EPPDEV.CN, All Rights Preserved!
 * License: Anti-996 License 1.0
 * Auto created by eppdev-jee(http://jee.eppdev.cn)!
 */

package cn.eppdev.mlib.orm.reg.service.auto;

import cn.eppdev.mlib.orm.commons.service.BasicService;
import cn.eppdev.mlib.orm.reg.dao.RegModelProviderMapDao;
import cn.eppdev.mlib.orm.reg.entity.RegModelProviderMap;
import cn.eppdev.mlib.orm.reg.param.RegModelProviderMapParam;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * reg_model_provider_map对应的基础Service类，请勿修改，代码生成时会自动进行覆盖
 * @author jinlong.hao
 */
public abstract class _RegModelProviderMapService extends BasicService<RegModelProviderMap, RegModelProviderMapParam> {

    @Autowired
    RegModelProviderMapDao dao;

    @Override
    public RegModelProviderMapDao getDao() {
        return dao;
    }


    @Override
    public boolean exists(RegModelProviderMap entity) {

        // 判断逻辑主键
        RegModelProviderMapParam param = new RegModelProviderMapParam();
        param.setModelId(entity.getModelId());
        param.setProviderInstanceName(entity.getProviderInstanceName());
        param.set_pageNum(1);
        param.set_pageSize(1);
        PageInfo<RegModelProviderMap> pageInfo = list(param);
        if (pageInfo.getTotal() > 0) {
            if (pageInfo.getList().get(0).getId().equals(entity.getId())){
                return false;
            }
            return true;
        }

        return false;
    }

}
