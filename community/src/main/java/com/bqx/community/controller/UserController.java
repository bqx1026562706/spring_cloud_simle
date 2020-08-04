package com.bqx.community.controller;

import com.bqx.community.pojo.User;
import com.bqx.community.service.UserService;
import com.bqx.community.utils.FileUtil;
import com.sun.java.browser.plugin2.liveconnect.v1.Result;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("getImageFromHttp")
    public  String getImageFromHttp(String urlStr) throws IOException {
        userService.getImageFromHttp(urlStr);
        return  "";
    }


    /**
     * 替换字符
     *
     * @param stb    source
     * @param oldStr 被替换的字符串
     * @param newStr 替换oldStr
     */
    public static StringBuilder replaceAll(StringBuilder stb, String oldStr, String newStr) {
        if (stb == null || oldStr == null || newStr == null || stb.length() == 0 || oldStr.length() == 0) {
            return stb;
        }
        int index = stb.indexOf(oldStr);
        if (index > -1 && !oldStr.equals(newStr)) {
            int lastIndex = 0;
            while (index > -1) {
                stb.replace(index, index + oldStr.length(), newStr);
                lastIndex = index + newStr.length();
                index = stb.indexOf(oldStr, lastIndex);
            }
        }
        return stb;
    }


    @PostMapping("/mapUpload")
    public Result uploadPhoto(@RequestParam("file") MultipartFile file,HttpServletRequest request){
        if(!file.isEmpty()) {
            // 获取文件名称,包含后缀
            String fileName = file.getOriginalFilename();
        }
        return null;
    }

    @RequestMapping("/excleDownLoad")
    public  byte[]  excleDownLoad(HttpServletRequest req , HttpServletResponse res){
            try {
                //获取输入流，原始模板位置
                String filePath = getClass().getResource("/excelTemplate.xlsx").getPath();
                InputStream bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
                //假如以中文名下载的话，设置下载文件名称
                String filename = "简历导入模板.xlsx";
                //转码，免得文件名中文乱码
                filename = URLEncoder.encode(filename, "UTF-8");
                //设置文件下载头
                res.addHeader("Content-Disposition", "attachment;filename=" + filename);
                //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
                res.setContentType("multipart/form-data");
                BufferedOutputStream out = new BufferedOutputStream(res.getOutputStream());
                int len = 0;
                while ((len = bis.read()) != -1) {
                    out.write(len);
                    out.flush();
                }
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }

    // 本地单个文件的上传
    @PostMapping("/upload")
    public String upload(MultipartFile uploadFile, HttpServletRequest request) {


        String realPath = request.getSession().getServletContext().getRealPath("/uploadFile/");
        File dir = new File(realPath);
        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
            dir.mkdirs();
        }
        try {
            String filename = uploadFile.getOriginalFilename();
            String suffix = filename.substring(filename.indexOf(".") + 1).toLowerCase();

            //服务端保存的文件对象
            File fileServer = new File(dir, filename);
            System.out.println("file文件真实路径:" + fileServer.getAbsolutePath());
            //2，实现上传
            uploadFile.transferTo(fileServer);
            String filePath = request.getScheme() + "://" +
                    request.getServerName() + ":"
                    + request.getServerPort()
                    + "/uploadFile/" + filename;
            //3，返回可供访问的网络路径
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
    //多个文件的上传
    @PostMapping("/uploads")
    public String uploads(MultipartFile[] uploadFiles, HttpServletRequest request) {
        //1，对文件数组做判空操作
        if (uploadFiles == null || uploadFiles.length < 1) {
            return "文件不能为空";
        }
        //2，定义文件的存储路径,
        String realPath = request.getSession().getServletContext().getRealPath("/uploadFile/");
        File dir = new File(realPath);
        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
            dir.mkdirs();
        }
        try {
            String filePathS = "";
            //3，遍历文件数组，一个个上传
            for (int i = 0; i < uploadFiles.length; i++) {
                MultipartFile uploadFile = uploadFiles[i];
                String filename = uploadFile.getOriginalFilename();
                //服务端保存的文件对象
                File fileServer = new File(dir, filename);
                System.out.println("file文件真实路径:" + fileServer.getAbsolutePath());
                //2，实现上传
                uploadFile.transferTo(fileServer);
                String filePath = request.getScheme() + "://" +
                        request.getServerName() + ":"
                        + request.getServerPort()
                        + "/uploadFile/" + filename;
                filePathS = filePathS + "\n" + filePath;
            }
            //4，返回可供访问的网络路径
            return filePathS;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }






    @RequestMapping("/log")
    public String testLog() {
        logger.debug("=====测试日志debug级别打印====");
        logger.info("======测试日志info级别打印=====");
        logger.error("=====测试日志error级别打印====");
        logger.warn("======测试日志warn级别打印=====");
        // 可以使用占位符打印出一些参数信息
        String str1 = "blog.itcodai.com";
        String str2 = "blog.csdn.net/eson_15";
        logger.info("======倪升武的个人博客：{}；倪升武的CSDN博客：{}", str1, str2);
        return "success";
    }

    @RequestMapping("/login")
    public String testLogin() {
        System.out.println("=========================" + "启动成功");
        return "=========================" + "启动成功";
    }

    /**
     * 根据id 找对应使用用户
     * @param
     * @return
     */
/*    @RequestMapping("/findByUserId/{userId}")
    public ResultModel<User> getUser(@PathVariable(value = "userId") String userId) {
        ResultModel<User> user=   userService.getUser(userId);
        return user;
    }*/



    @RequestMapping("/map")
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>(3);
        map.put("博客地址", "55");
        map.put("CSDN地址", "56");
        map.put("粉丝数量", 4153);
        return map;
    }



}
