package com.bqx.community.utils.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    /**
     * @param is    导入文件输入流
     * @param clazz Excel实体映射类
     * @return
     */
    public static List<Integer> readExcel(InputStream is, Class clazz) {

        BufferedInputStream bis = null;
        ExcelListener listener = new ExcelListener();
        List<Integer> currents = new ArrayList<>();
        try {
            bis = new BufferedInputStream(is);
            // 解析每行结果在listener中处理
        /*    listener.setCompanyId(companyId);
            listener.setUserId(userId);
            listener.setUserName(userName);
            listener.setPositionId(positionId);*/
            ExcelReader excelReader = EasyExcelFactory.getReader(bis, listener);
            excelReader.read(new Sheet(1, 1, clazz));
            /*if (listener.getError() == -1){
                currents.add(0);
                return currents;
            }
            currents = listener.getCurrents();*/
        } catch (Exception e) {
            e.printStackTrace();
            ArrayList<Integer> integers = new ArrayList<Integer>();
            integers.add(0);
            Logger logger = LogManager.getLogger(ExcelUtil.class);
            logger.error("[上传Excel格式简历·分装成实体类时出错] --> ExcelUtils/readExcel");
            return integers;
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return currents;
    }


    /**
     * @param os    文件输出流
     * @param clazz Excel实体映射类
     * @param data  导出数据
     * @return
     */
    public static Boolean writeExcel(OutputStream os, Class clazz, List<? extends BaseRowModel> data) {
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(os);
            ExcelWriter writer = new ExcelWriter(bos, ExcelTypeEnum.XLSX);
            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet1 = new Sheet(1, 0, clazz);
            writer.write(data, sheet1);
            writer.finish();
        } catch (Exception e) {
            Logger logger = LogManager.getLogger(ExcelUtil.class);
            logger.error("[下载Excel格式简历·分装成文件时出错] --> ExcelUtils/writeExcel");
            e.printStackTrace();
            return false;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
