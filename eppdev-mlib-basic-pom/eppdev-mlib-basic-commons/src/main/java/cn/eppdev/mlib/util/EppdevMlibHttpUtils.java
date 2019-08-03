/* FileName: EppdevMlibHttpUtils.java
 * Copyright jinlong.hao(jinlong.hao@eppdev.cn).  All Rights Preserved!
 * Licensed By Anti-996 License
 */

package cn.eppdev.mlib.util;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import cn.eppdev.mlib.commons.rest.RestResult;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 本次Http请求相关的工具类，用于完成各个实例之间的接口调用
 *
 * @author jinlong.hao
 */
public class EppdevMlibHttpUtils {
    static Logger logger = LoggerFactory.getLogger(EppdevMlibHttpUtils.class);


    /**
     * 获取get请求内容
     *
     * @param instance 实例类型，主要使用其basicUrl
     * @param url      其相对url
     * @return 执行返回的结果
     */
    public static String get(EppdevMlibInstance instance, String url) {
        logger.debug("get-instance:{}", instance);
        logger.debug("get-url:{}", url);
        return get(instance.getBasicUrl(), url);
    }


    /**
     * 获取get请求内容
     *
     * @param basicUrl 实例的基础url
     * @param url      其相对url
     * @return 执行返回的结果
     */
    public static String get(String basicUrl, String url) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(basicUrl + url);
        String result = null;
        try {
            HttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            logger.error("Error when do get: {}/{}", basicUrl, url);
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            }
        }
        return result;
    }


    /**
     * 执行post请求，将请求结果转换为字符串
     *
     * @param instance    要请求的实例信息，主要使用其中的basicUrl
     * @param url         其相对url
     * @param postContent 请求的responseBody内容
     * @return 返回的结果
     */
    public static String post(EppdevMlibInstance instance, String url, String postContent) {
        logger.debug("post-instance:{}", instance);
        logger.debug("post-url:{}", url);
        logger.debug("post-postContent:{}", postContent);
        return post(instance.getBasicUrl(), url, postContent);
    }


    /**
     * 执行post请求，将请求结果转换为字符串
     *
     * @param basicUrl    要请求的基础url
     * @param url         其相对url
     * @param postContent 请求的responseBody内容
     * @return 返回的结果
     */
    public static String post(String basicUrl, String url, String postContent) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(basicUrl + url);
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        post.setEntity(new StringEntity(postContent, "UTF-8"));
        String result = null;
        try {
            HttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            logger.error("Error when do post: {}/{}", basicUrl, url);
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            }
        }
        return result;
    }

//
//    /**
//     * 调用get方法，并将返回值转换为RestResult对象
//     *
//     * @param instance 请求的实例规格信息，主要使用其中的basicUrl
//     * @param url      本次调用的相对url
//     * @param <T>      返回的RestResult中的结果数据类型
//     * @return 返回的RestResult对象
//     */
//    public static <T> RestResult<T> getRestResult(EppdevMlibInstance instance, String url, Class<T> cls) {
//        String resultContent = get(instance, url);
//        if (resultContent == null) {
//            return new RestResult<>(RestResult.STATUS_FAILED_CALL_API, "接口调用异常", null);
//        }
//        RestResult<T> restResult = EppdevMlibJsonUtils.readValue(resultContent, new TypeReference<RestResult<T>>() {
//        });
//        if (null == restResult) {
//            restResult = new RestResult<>(RestResult.STATUS_FAILED_PARSE_JSON, "接口解析异常", null);
//        }
//        return restResult;
//    }
//
//
//    /**
//     * 调用get方法，并将返回值转换为RestResult对象
//     *
//     * @param basicUrl 请求的实例的basicUrl
//     * @param url      本次调用的相对url
//     * @param <T>      返回的RestResult中的结果数据类型
//     * @return 返回的RestResult对象
//     */
//    public static <T> RestResult<T> getRestResult(String basicUrl, String url, Class<T> cls) {
//        String resultContent = get(basicUrl, url);
//        if (resultContent == null) {
//            return new RestResult<>(RestResult.STATUS_FAILED_CALL_API, "接口调用异常", null);
//        }
//        RestResult<T> restResult = EppdevMlibJsonUtils.readValue(resultContent, new TypeReference<RestResult<T>>() {
//        });
//        if (null == restResult) {
//            restResult = new RestResult<>(RestResult.STATUS_FAILED_PARSE_JSON, "接口解析异常", null);
//        }
//        return restResult;
//    }
//
//
//    /**
//     * 调用post接口，并将返回值转换为RestResult对象
//     *
//     * @param instance    请求的实例规格信息，主要使用其中的basicUrl信息
//     * @param url         本次调用的相对url
//     * @param postContent 要RequestBody中post的数据
//     * @param <T>         返回的RestResult中的结果数据类型
//     * @return 返回的RestResult对象
//     */
//    public static <T> RestResult<T> postRestResult(EppdevMlibInstance instance, String url,
//                                                   String postContent, Class<T> cls) {
//        String resultContent = post(instance, url, postContent);
//        if (resultContent == null) {
//            return new RestResult<>(RestResult.STATUS_FAILED_CALL_API, "接口调用异常", null);
//        }
//        RestResult<T> restResult = EppdevMlibJsonUtils.readValue(resultContent, new TypeReference<RestResult<T>>() {
//        });
//        if (null == restResult) {
//            restResult = new RestResult<>(RestResult.STATUS_FAILED_PARSE_JSON, "接口解析异常", null);
//        }
//        return restResult;
//    }
//
//    /**
//     * 调用post接口，并将返回值转换为RestResult对象
//     *
//     * @param basicUrl    请求的实例的basicUrl
//     * @param url         本次调用的相对url
//     * @param postContent 要RequestBody中post的数据
//     * @param <T>         返回的RestResult中的结果数据类型
//     * @return 返回的RestResult对象
//     */
//    public static <T> RestResult<T> postRestResult(String basicUrl, String url,
//                                                   String postContent, Class<T> cls) {
//        String resultContent = post(basicUrl, url, postContent);
//        if (resultContent == null) {
//            return new RestResult<>(RestResult.STATUS_FAILED_CALL_API, "接口调用异常", null);
//        }
//        RestResult<T> restResult = EppdevMlibJsonUtils.readValue(resultContent, new TypeReference<RestResult<T>>() {
//        });
//        if (null == restResult) {
//            restResult = new RestResult<>(RestResult.STATUS_FAILED_PARSE_JSON, "接口解析异常", null);
//        }
//        return restResult;
//    }

}
