/* FileName: EppdevMlibRandomUtils.java
 * Copyright jinlong.hao(jinlong.hao@eppdev.cn).  All Rights Preserved!
 * Licensed By Anti-996 License
 */

package cn.eppdev.mlib.util;

import cn.eppdev.mlib.commons.instance.EppdevMlibInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

/**
 * 接口的随机调用信息
 * @author jinlong.hao
 */
public class EppdevMlibRandomUtils {
    static Logger logger = LoggerFactory.getLogger(EppdevMlibRandomUtils.class);

    /**
     * 随机获取一个EppdevMlibProvider信息，用于进行接口调用
     *
     * @param instanceList 同时可用的client信息
     * @return 一个随机的可用的client
     */
    public static EppdevMlibInstance getRamdomClient(List<EppdevMlibInstance> instanceList) {
        if (null == instanceList || instanceList.size() == 0) {
            return null;
        }
        Random random = new Random();
        return instanceList.get(random.nextInt(instanceList.size()));
    }

}
