/* FileName: _RegModelInfoDao.java
 * Copyright EPPDEV.CN, All Rights Preserved!
 * License: Anti-996 License 1.0
 * Auto created by eppdev-jee(http://jee.eppdev.cn)!
 */

package cn.eppdev.mlib.orm.reg.dao.auto;

import cn.eppdev.mlib.orm.commons.dao.BasicDao;
import cn.eppdev.mlib.orm.reg.entity.RegModelInfo;
import cn.eppdev.mlib.orm.reg.param.RegModelInfoParam;
import org.apache.ibatis.annotations.Param;

/**
 * reg_model_info对应的基础Dao，请勿修改，代码生成时会自动进行覆盖
 * @author jinlong.hao
 */
public interface _RegModelInfoDao extends BasicDao<RegModelInfo, RegModelInfoParam> {
    public int realDelete(@Param("id") String id);

}
