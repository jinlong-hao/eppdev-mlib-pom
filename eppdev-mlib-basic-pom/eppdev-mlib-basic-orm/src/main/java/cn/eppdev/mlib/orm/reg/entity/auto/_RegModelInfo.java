/* FileName: _RegModelInfo.java
 * Copyright EPPDEV.CN, All Rights Preserved!
 * License: Anti-996 License 1.0
 * Auto created by eppdev-jee(http://jee.eppdev.cn)!
 */

package cn.eppdev.mlib.orm.reg.entity.auto;

import cn.eppdev.mlib.orm.commons.entity.BasicEntity;

/**
 * reg_model_info对应的基础实体类，请勿修改，代码生成时会自动进行覆盖
 * @author jinlong.hao
 */
public class _RegModelInfo extends BasicEntity {

    /* ***********************************************
     * static properties
     * **********************************************/
    // static properties for model_id
    public static final String COLUMN_MODEL_ID_ = "model_id";
    public static final String COLUMN_MODEL_ID_ASC_ = "model_id asc";
    public static final String COLUMN_MODEL_ID_DESC_ = "model_id desc";

    // static properties for model_type
    public static final String COLUMN_MODEL_TYPE_ = "model_type";
    public static final String COLUMN_MODEL_TYPE_ASC_ = "model_type asc";
    public static final String COLUMN_MODEL_TYPE_DESC_ = "model_type desc";

    // static properties for model_content
    public static final String COLUMN_MODEL_CONTENT_ = "model_content";
    public static final String COLUMN_MODEL_CONTENT_ASC_ = "model_content asc";
    public static final String COLUMN_MODEL_CONTENT_DESC_ = "model_content desc";

    // static properties for model_weight
    public static final String COLUMN_MODEL_WEIGHT_ = "model_weight";
    public static final String COLUMN_MODEL_WEIGHT_ASC_ = "model_weight asc";
    public static final String COLUMN_MODEL_WEIGHT_DESC_ = "model_weight desc";



    /* ***********************************************
     * properties
     * **********************************************/
    // properties for model_id
    private String modelId;

    // properties for model_type
    private String modelType;

    // properties for model_content
    private String modelContent;

    // properties for model_weight
    private Float modelWeight;


    /* ***********************************************
     * getters
     * **********************************************/
    // getters for model_id
    public String getModelId() {
        return modelId;
    }

    // getters for model_type
    public String getModelType() {
        return modelType;
    }

    // getters for model_content
    public String getModelContent() {
        return modelContent;
    }

    // getters for model_weight
    public Float getModelWeight() {
        return modelWeight;
    }


    /* ***********************************************
     * setters
     * **********************************************/
    // getters for model_id
    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    // getters for model_type
    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    // getters for model_content
    public void setModelContent(String modelContent) {
        this.modelContent = modelContent;
    }

    // getters for model_weight
    public void setModelWeight(Float modelWeight) {
        this.modelWeight = modelWeight;
    }

}
