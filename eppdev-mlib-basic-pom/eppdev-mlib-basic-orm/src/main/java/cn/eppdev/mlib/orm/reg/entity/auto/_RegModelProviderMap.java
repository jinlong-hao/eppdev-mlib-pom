/* FileName: _RegModelProviderMap.java
 * Copyright EPPDEV.CN, All Rights Preserved!
 * License: Anti-996 License 1.0
 * Auto created by eppdev-jee(http://jee.eppdev.cn)!
 */

package cn.eppdev.mlib.orm.reg.entity.auto;

import cn.eppdev.mlib.orm.commons.entity.BasicEntity;

/**
 * reg_model_provider_map对应的基础实体类，请勿修改，代码生成时会自动进行覆盖
 * @author jinlong.hao
 */
public class _RegModelProviderMap extends BasicEntity {

    /* ***********************************************
     * static properties
     * **********************************************/
    // static properties for model_id
    public static final String COLUMN_MODEL_ID_ = "model_id";
    public static final String COLUMN_MODEL_ID_ASC_ = "model_id asc";
    public static final String COLUMN_MODEL_ID_DESC_ = "model_id desc";

    // static properties for provider_instance_name
    public static final String COLUMN_PROVIDER_INSTANCE_NAME_ = "provider_instance_name";
    public static final String COLUMN_PROVIDER_INSTANCE_NAME_ASC_ = "provider_instance_name asc";
    public static final String COLUMN_PROVIDER_INSTANCE_NAME_DESC_ = "provider_instance_name desc";



    /* ***********************************************
     * properties
     * **********************************************/
    // properties for model_id
    private String modelId;

    // properties for provider_instance_name
    private String providerInstanceName;


    /* ***********************************************
     * getters
     * **********************************************/
    // getters for model_id
    public String getModelId() {
        return modelId;
    }

    // getters for provider_instance_name
    public String getProviderInstanceName() {
        return providerInstanceName;
    }


    /* ***********************************************
     * setters
     * **********************************************/
    // getters for model_id
    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    // getters for provider_instance_name
    public void setProviderInstanceName(String providerInstanceName) {
        this.providerInstanceName = providerInstanceName;
    }

}
