/* FileName: RegModelProviderMapParam.java
 * Copyright EPPDEV.CN, All Rights Preserved!
 * License: Anti-996 License 1.0
 * Auto created by eppdev-jee(http://jee.eppdev.cn)!
 */

package cn.eppdev.mlib.orm.reg.param.auto;

import cn.eppdev.mlib.orm.commons.param.BasicParam;

/**
 * reg_model_provider_map对应的基础参数类，请勿修改，代码生成时会自动进行覆盖
 * @author jinlong.hao
 */
public class _RegModelProviderMapParam extends BasicParam {

    /* ***********************************************
     * properties
     * **********************************************/
    // properties for id
    private String id;
    private java.util.List<String> _inIdList;

    // properties for model_id
    private String modelId;

    // properties for provider_instance_name
    private String providerInstanceName;

    // properties for create_date
    private java.util.Date _leftLikeCreateDate;
    private java.util.Date _minCreateDate;
    private java.util.Date _maxCreateDate;

    // properties for create_by
    private String createBy;

    // properties for update_by
    private String updateBy;

    // properties for del_flag
    private Integer delFlag;


    /* ***********************************************
     * getters
     * **********************************************/
    // getters for id
    public String getId() {
        return id;
    }
    public java.util.List<String> get_inIdList() {
        return _inIdList;
    }

    // getters for model_id
    public String getModelId() {
        return modelId;
    }

    // getters for provider_instance_name
    public String getProviderInstanceName() {
        return providerInstanceName;
    }

    // getters for create_date
    public java.util.Date get_leftLikeCreateDate() {
        return _leftLikeCreateDate;
    }
    public java.util.Date get_minCreateDate() {
        return _minCreateDate;
    }
    public java.util.Date get_maxCreateDate(){
        return _maxCreateDate;
    }

    // getters for create_by
    public String getCreateBy() {
        return createBy;
    }

    // getters for update_by
    public String getUpdateBy() {
        return updateBy;
    }

    // getters for del_flag
    public Integer getDelFlag() {
        return delFlag;
    }


    /* ***********************************************
     * setters
     * **********************************************/
    // setters for id
    public void setId(String id) {
        this.id = id;
    }
    public void set_inId(java.util.List<String> _inIdList) {
        this._inIdList = _inIdList;
    }

    // setters for model_id
    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    // setters for provider_instance_name
    public void setProviderInstanceName(String providerInstanceName) {
        this.providerInstanceName = providerInstanceName;
    }

    // setters for create_date
    public void set_leftLikeCreateDate(java.util.Date _leftLikeCreateDate) {
        this._leftLikeCreateDate = _leftLikeCreateDate;
    }
    public void set_minCreateDate(java.util.Date _minCreateDate) {
        this._minCreateDate = _minCreateDate;
    }
    public void set_maxCreateDate(java.util.Date _maxCreateDate){
        this._maxCreateDate = _maxCreateDate;
    }

    // setters for create_by
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    // setters for update_by
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    // setters for del_flag
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

}
