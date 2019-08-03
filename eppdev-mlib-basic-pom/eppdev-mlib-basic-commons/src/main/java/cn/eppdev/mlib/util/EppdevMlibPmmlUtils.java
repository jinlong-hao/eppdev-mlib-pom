/* FileName: EppdevMlibPmmlUtils.java
 * Copyright EPPDEV.CN(jinlong.hao@eppdev.cn)  All Rights Preserved!
 * Licensed By ANTI-996 License V1.0
 */

package cn.eppdev.mlib.util;

import org.dmg.pmml.PMML;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.LoadingModelEvaluatorBuilder;
import org.jpmml.evaluator.visitors.DefaultVisitorBattery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;

/**
 * @author jinlong.hao
 */
public class EppdevMlibPmmlUtils {
    static Logger logger = LoggerFactory.getLogger(EppdevMlibPmmlUtils.class);

    /**
     * 验证pmml文件是否合法
     *
     * @param pmmlContent pmml文件内容
     * @return pmml文件是否合法
     */
    public static boolean valid(String pmmlContent) {
        try {
            PMML pmml = new PMML();
            Evaluator evaluator = new LoadingModelEvaluatorBuilder()
                    .setLocatable(false)
                    .setVisitors(new DefaultVisitorBattery())
                    //.setOutputFilter(OutputFilters.KEEP_FINAL_RESULTS)
                    .load(new ByteArrayInputStream(pmmlContent.getBytes()))
                    .build();
            evaluator.verify();
            System.out.println(evaluator.getSummary());
            return true;
        } catch (SAXException e) {
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            e.printStackTrace();
        } catch (JAXBException e) {
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 根据pmml内容，返回一个Evaluator对象
     *
     * @param pmmlContent pmml文件内容
     * @return 检查成功则返回一个Evaluator对象，否则返回一个null
     */
    public static Evaluator getEvaluatorByContent(String pmmlContent) {
        try {
            PMML pmml = new PMML();
            Evaluator evaluator = new LoadingModelEvaluatorBuilder()
                    .setLocatable(false)
                    .setVisitors(new DefaultVisitorBattery())
                    //.setOutputFilter(OutputFilters.KEEP_FINAL_RESULTS)
                    .load(new ByteArrayInputStream(pmmlContent.getBytes()))
                    .build();
            return evaluator.verify();
        } catch (SAXException e) {
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            e.printStackTrace();
        } catch (JAXBException e) {
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            e.printStackTrace();
        }
        return null;
    }

    /*
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/jinlong.hao/test2.pmml");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        Evaluator evaluator = getEvaluatorByContent(sb.toString());
        Map<FieldName, ?> map
                = evaluator.evaluate(new HashMap<>());
        System.out.println(map);
    }
     */
}
