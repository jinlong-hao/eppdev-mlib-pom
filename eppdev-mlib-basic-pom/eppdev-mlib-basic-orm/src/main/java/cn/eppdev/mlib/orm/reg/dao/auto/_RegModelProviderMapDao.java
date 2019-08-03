/* FileName: _RegModelProviderMapDao.java
 * Copyright EPPDEV.CN, All Rights Preserved!
 * License: Anti-996 License 1.0
 * Auto created by eppdev-jee(http://jee.eppdev.cn)!
 */

package cn.eppdev.mlib.orm.reg.dao.auto;

import cn.eppdev.mlib.orm.commons.dao.BasicDao;
import cn.eppdev.mlib.orm.reg.entity.RegModelProviderMap;
import cn.eppdev.mlib.orm.reg.param.RegModelProviderMapParam;
import org.apache.ibatis.annotations.Param;

/**
 * reg_model_provider_map对应的基础Dao，请勿修改，代码生成时会自动进行覆盖
 * @author jinlong.hao
 */
public interface _RegModelProviderMapDao extends BasicDao<RegModelProviderMap, RegModelProviderMapParam> {
    public int realDelete(@Param("id") String id);

}
