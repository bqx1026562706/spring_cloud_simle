package com.bqx.community.utils;

import java.io.*;

public class FileUtil {

    //静态方法：三个参数：文件的二进制，文件路径，文件名
    //通过该方法将在指定目录下添加指定文件
    public static void fileupload(byte[] file,String filePath,String fileName) throws  IOException {
        //目标目录
        File targetfile = new File(filePath);
        if(targetfile.exists()) {
            targetfile.mkdirs();
        }

        //二进制流写入
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    /**
     *
     * @param fileName 文件名称
     * @param inputStream 文件流
     * @throws IOException
     */
    public static void fileuploadInput(String fileName, InputStream inputStream) throws  IOException {


    }

}
