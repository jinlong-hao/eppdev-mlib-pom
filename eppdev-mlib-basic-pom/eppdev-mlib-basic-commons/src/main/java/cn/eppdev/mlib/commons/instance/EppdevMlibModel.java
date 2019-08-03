/* FileName: EppdevMlibModel.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License v1.0
 */

package cn.eppdev.mlib.commons.instance;

import cn.eppdev.mlib.util.EppdevMlibJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jinlong.hao
 */
public class EppdevMlibModel {
    static Logger logger = LoggerFactory.getLogger(EppdevMlibModel.class);

    /**
     * 模型id
     */
    private String modelId;

    /**
     * 模型类型说明
     */
    private String modelType;

    /**
     * 模型pmml内容
     */
    private String modelContent;

    /**
     * 模型备注信息
     */
    private String remark;

    public EppdevMlibModel(){

    }

    public EppdevMlibModel(String modelId, String modelType, String modelContent, String remark) {
        this.modelId = modelId;
        this.modelType = modelType;
        this.modelContent = modelContent;
        this.remark = remark;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getModelContent() {
        return modelContent;
    }

    public void setModelContent(String modelContent) {
        this.modelContent = modelContent;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return EppdevMlibJsonUtils.toJson(this);
    }
}
