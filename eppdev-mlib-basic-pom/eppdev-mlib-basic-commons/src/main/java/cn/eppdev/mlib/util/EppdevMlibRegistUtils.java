/* FileName: EppdevMlibRegistUtils.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License v1.0
 */

package cn.eppdev.mlib.util;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import cn.eppdev.mlib.commons.rest.RestResult;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * @author jinlong.hao
 */
public class EppdevMlibRegistUtils {
    static Logger logger = LoggerFactory.getLogger(EppdevMlibRegistUtils.class);

    public static final String REGISTER_URL = "/register";

    public static final String MODEL_PROVIDER_URL = "/model/{modelId}/providers";

    public static final String PROVIDER_LIST_URL = "/provider/list";

    public static boolean register(EppdevMlibInstance register, EppdevMlibInstance client) {
        String resultContent = EppdevMlibHttpUtils.post(register, REGISTER_URL, client.toString());
        if (resultContent == null) {
            return false;
        } else {
            RestResult<String> restResult = EppdevMlibJsonUtils.readValue(resultContent,
                    new TypeReference<RestResult<String>>() {
                    });
            if (restResult == null) {
                return false;
            } else {
                return RestResult.STATUS_SUCCESS == restResult.getStatus();
            }
        }
    }

    public static boolean register(String registBasicUrl, EppdevMlibInstance client) {
        String resultContent = EppdevMlibHttpUtils.get(registBasicUrl, REGISTER_URL);
        if (resultContent == null) {
            return false;
        } else {
            RestResult<String> restResult = EppdevMlibJsonUtils.readValue(resultContent,
                    new TypeReference<RestResult<String>>() {
                    });
            if (restResult == null) {
                return false;
            } else {
                return RestResult.STATUS_SUCCESS == restResult.getStatus();
            }
        }
    }

    public static RestResult<Set<String>> listProvidersByModel(EppdevMlibInstance register, String modelId) {
        String resultContent = EppdevMlibHttpUtils.get(register.getBasicUrl(), MODEL_PROVIDER_URL.replace("{modelId}", modelId));
        if (resultContent == null) {
            return new RestResult<>(RestResult.STATUS_FAILED_CALL_API, "获取失败", null);
        } else {
            RestResult<Set<String>> restResult = EppdevMlibJsonUtils.readValue(resultContent,
                    new TypeReference<RestResult<Set<String>>>(){});
            if (restResult == null) {
                return new RestResult<>(RestResult.STATUS_FAILED_PARSE_JSON, "获取失败", null);
            }
             else {
                 return restResult;
            }
        }
    }

    public static RestResult<List<EppdevMlibInstance>> listAllProviders(EppdevMlibInstance register) {
        String resultContent = EppdevMlibHttpUtils.get(register.getBasicUrl(), PROVIDER_LIST_URL);
        if (resultContent == null) {
            return new RestResult<>(RestResult.STATUS_FAILED_CALL_API, "获取失败", null);
        } else {
            RestResult<List<EppdevMlibInstance>> restResult = EppdevMlibJsonUtils.readValue(resultContent,
                    new TypeReference<RestResult<List<EppdevMlibInstance>>>(){});
            if (restResult == null) {
                return new RestResult<>(RestResult.STATUS_FAILED_PARSE_JSON, "获取失败", null);
            }
            else {
                return restResult;
            }
        }
    }

}
