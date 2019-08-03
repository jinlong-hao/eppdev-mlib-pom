/* FileName: EppdevMlibJsonUtils.java
 * Copyright jinlong.hao(jinlong.hao@eppdev.cn).  All Rights Preserved!
 * Licensed By Anti-996 License
 */

package cn.eppdev.mlib.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author jinlong.hao
 */
public class EppdevMlibJsonUtils {
    static Logger logger = LoggerFactory.getLogger(EppdevMlibJsonUtils.class);

    static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * JavaBean转换为json字符串
     * @param bean 要转换为json的javabean
     * @return json字符串
     */
    public static String toJson(Object bean){
        try {
            return objectMapper.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
        }
        return null;
    }


    /**
     * 将json反解析为javabean
     * @param content json内容
     * @param valueType java的类型
     * @param <T> JavaBean类型
     * @return javaBean
     */
    public static <T> T readValue(String content, Class<T> valueType){
        try {
            return objectMapper.readValue(content, valueType);
        } catch (IOException e) {
            logger.error("Error parse {} to javabean", content);
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
        }
        return null;
    }

    /**
     * 将json反解析为javabean
     * @param content json内容
     * @param typeReference JavaBean类型，用于支持泛型
     * @param <T> JavaBean类型
     * @return javaBean
     */
    public static <T> T readValue(String content, TypeReference typeReference) {
        try {
            return objectMapper.readValue(content, typeReference);
        } catch (IOException e) {
            logger.error("Error parse {} to javabean", content);
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
        }
        return null;
    }


}
