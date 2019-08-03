/* FileName: EppdevMlibInstanceInfoUtils.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License v1.0
 */

package cn.eppdev.mlib.util;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import cn.eppdev.mlib.commons.rest.RestResult;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jinlong.hao
 */
public class EppdevMlibInstanceInfoUtils {
    static Logger logger = LoggerFactory.getLogger(EppdevMlibInstanceInfoUtils.class);

    public static final String INFO_URL = "/info";

    public static RestResult<EppdevMlibInstance> info(String basicUrl) {
        String resultContent = EppdevMlibHttpUtils.get(basicUrl, INFO_URL);
        if (resultContent == null) {
            return new RestResult<>(RestResult.STATUS_FAILED_CALL_API, "接口调用错误", null);
        } else {
            RestResult<EppdevMlibInstance> restResult = EppdevMlibJsonUtils.readValue(resultContent,
                    new TypeReference<RestResult<EppdevMlibInstance>>() {
            });
            if (restResult == null) {
                return new RestResult<>(RestResult.STATUS_FAILED_PARSE_JSON, "解析错误", null);
            } else {
                return restResult;
            }
        }
    }
}
