/* FileName: RestResult.java
 * Copyright jinlong.hao(jinlong.hao@eppdev.cn).  All Rights Preserved!
 * Licensed By Anti-996 License
 */

package cn.eppdev.mlib.commons.rest;

import cn.eppdev.mlib.util.EppdevMlibJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jinlong.hao
 */
public class RestResult<T> {
    static Logger logger = LoggerFactory.getLogger(RestResult.class);


    public static final int STATUS_SUCCESS = 2000;
    public static final int STATUS_FAILED_CALL_API = 3001;
    public static final int STATUS_FAILED_PARSE_JSON = 3002;
    public static final int STATUS_MODEL_NOT_FOUND = 4001;
    public static final int STATUS_MODEL_CALC_ERROR = 4002;
    public static final int STATUS_INTERNEL_ERROR = 6001;

    /**
     * 状态码：<br />
     * <ul>
     * <li>2000：正常返回</li>
     * <li>3001: 接口调用错误</li>
     * <li>3002: 内容解析错误</li>
     * <li>其他：报错</li>
     * </ul>
     */
    private Integer status;
    /**
     * 信息
     */
    private String message;
    /**
     * 对应的具体数据
     */
    private T data;

    public RestResult() {
    }

    public RestResult(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return EppdevMlibJsonUtils.toJson(this);
    }
}
