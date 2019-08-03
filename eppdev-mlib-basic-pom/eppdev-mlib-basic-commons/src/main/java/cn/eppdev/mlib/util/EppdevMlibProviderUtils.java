/* FileName: EppdevMlibProviderUtils.java
 * Copyright jinlong.hao(jinlong.hao@eppdev.cn).  All Rights Preserved!
 * Licensed By Anti-996 License
 */

package cn.eppdev.mlib.util;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import cn.eppdev.mlib.commons.instance.EppdevMlibModel;
import cn.eppdev.mlib.commons.rest.RestResult;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jinlong.hao
 */
public class EppdevMlibProviderUtils {
    static Logger logger = LoggerFactory.getLogger(EppdevMlibProviderUtils.class);

    public static final String EXECUTE_URL = "/model/{modelId}/calc";
    public static final String DEPLOY_URL = "/deploy";
    public static final String UNDEPLOY_URL = "/model/{modelId}/undeploy";
    public static final String MODEL_STATUS = "/model/{modelId}/status";

    /**
     * 执行模型调用接口，获取模型调用结果
     *
     * @param provider    模型计算服务的provider
     * @param postContent 要计算的数据内容
     * @return 模型调用结果
     */
    public static RestResult<String> execute(EppdevMlibInstance provider, String modelId, String postContent) {
        String resultContent = EppdevMlibHttpUtils.post(provider, EXECUTE_URL.replace("{modelId}", modelId), postContent);
        if (resultContent == null) {
            return new RestResult<>(RestResult.STATUS_FAILED_CALL_API, "接口调用错误", null);
        } else {
            RestResult<String> restResult = EppdevMlibJsonUtils.readValue(resultContent, new TypeReference<RestResult<String>>() {
            });
            if (restResult == null) {
                return new RestResult<>(RestResult.STATUS_FAILED_PARSE_JSON, "解析错误", null);
            } else {
                return restResult;
            }
        }
    }


    /**
     * 调用Provider的模型发布接口，完成模型发布
     * @param provider 要发布模型的目标Provider
     * @param modelInfo 要发布的模型
     * @return 是否发布成功
     */
    public static RestResult<String> deploy(EppdevMlibInstance provider, EppdevMlibModel modelInfo){
        String resultContent = EppdevMlibHttpUtils.post(provider, DEPLOY_URL, modelInfo.toString());
        if (resultContent == null) {
            return new RestResult<>(RestResult.STATUS_FAILED_CALL_API, "接口调用错误", null);
        } else {
            RestResult<String> restResult = EppdevMlibJsonUtils.readValue(resultContent, new TypeReference<RestResult<String>>() {
            });
            if (restResult == null) {
                return new RestResult<>(RestResult.STATUS_FAILED_PARSE_JSON, "解析错误", null);
            } else {
                return restResult;
            }
        }
    }

    /**
     * 调用provider的模型卸载接口，完成模型的反加载
     * @param provider 要卸载模型的provider
     * @param modelId 要卸载的模型id
     * @return 是否发布成功
     */
    public static RestResult<String> undeploy(EppdevMlibInstance provider, String modelId) {
        String resultContent = EppdevMlibHttpUtils.get(provider, UNDEPLOY_URL.replace("{modelId}", modelId));
        if (resultContent == null) {
            return new RestResult<>(RestResult.STATUS_FAILED_CALL_API, "接口调用错误", null);
        } else {
            RestResult<String> restResult = EppdevMlibJsonUtils.readValue(resultContent, new TypeReference<RestResult<String>>() {
            });
            if (restResult == null) {
                return new RestResult<>(RestResult.STATUS_FAILED_PARSE_JSON, "解析错误", null);
            } else {
                return restResult;
            }
        }
    }

    /**
     * 检查某provider是否已部署某模型
     * @param provider provider信息
     * @param modelId 模型id
     * @return 是否已部署该模型
     */
    public static RestResult<Boolean> hasModel(EppdevMlibInstance provider, String modelId) {
        String resultContent = EppdevMlibHttpUtils.get(provider, MODEL_STATUS.replace("{modelId}", modelId));
        if (resultContent == null) {
            return new RestResult<>(RestResult.STATUS_FAILED_CALL_API, "接口调用错误", null);
        } else {
            RestResult<Boolean> restResult = EppdevMlibJsonUtils.readValue(resultContent, new TypeReference<RestResult<Boolean>>() {
            });
            if (restResult == null) {
                return new RestResult<>(RestResult.STATUS_FAILED_PARSE_JSON, "解析错误", null);
            } else {
                return restResult;
            }
        }
    }
}
