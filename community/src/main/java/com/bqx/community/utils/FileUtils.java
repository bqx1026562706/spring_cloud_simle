package com.bqx.community.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

/**
 * Description: 文件工具类
 * Created on 2020/7/21 14:59
 *
 * @author Wenhao.Lee
 * @version 1.0
 */
public class FileUtils {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 多次使用输入流
     *
     * @param inputStream 输入流
     */
    public static ByteArrayOutputStream inputStreamCache(InputStream inputStream) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) > -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        byteArrayOutputStream.flush();
        return byteArrayOutputStream;
    }

    /**
     * 获取文件大小
     *
     * @param inputStream 输入流
     */
    public static long getFileSize(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return 0;
        }
        byte[] bytes = new byte[1024];
        int len;
        long size = 0;
        while ((len = inputStream.read(bytes, 0, bytes.length)) != -1) {
            size += len;
        }
        return size;
    }

  /*  *//**
     * 简历上传前的检查 （只检查姓名手机号邮箱）
     *
     * @param inputStream 简历文件流
     * @param index       文件下标
     * @param channel     渠道名称
     *//*
    public static String resumeFileCheck(InputStream inputStream, String fileName, Integer index, String channel) throws Exception {
        Map<String, Object> parseMap = parseResume(inputStream, fileName, index, channel);
        PersonalInfo info = (PersonalInfo) parseMap.get(Constants.ResumeConstants.RESUME_PERSON_INFO);
        if (info == null) {
            return "个人基本信息不存在";
        }
        if (StringUtils.isBlank(info.getPersonChineseName())) {
            return "姓名为空";
        }
        if (StringUtils.isBlank(info.getPersonTel())) {
            return "手机号为空";
        }
        if (StringUtils.isBlank(info.getPersonEmail())) {
            return "邮箱为空";
        }
        return StringUtils.EMPTY;
    }*/

    /**
     * 简历解析
     *
     * @param inputStream 简历文件流
     * @param index       文件下标
     * @param channel     渠道名称
     */
   /* public static Map<String, Object> parseResume(InputStream inputStream, String fileName, Integer index, String channel) throws Exception {
        Map<String, Object> resultMap;
        switch (channel) {
            case "智联":
                resultMap = ZlHtmlResolveUtil.hmtResolve(inputStream, fileName);
                break;
            case "前程无忧":
                resultMap = QcwyHtmlResolveUtil.hmtResolve(inputStream, fileName);
                break;
            case "拉勾网":
                resultMap = LgHtmlResolveUtil.hmtResolve(inputStream, fileName);
                break;
            default:
                throw new IllegalArgumentException("渠道信息填写错误");
        }
        resultMap.put(Constants.ResumeConstants.RESUME_FILE_FLAG, 0);
        resultMap.put(Constants.ResumeConstants.RESUME_FILE_INDEX, index);
        return resultMap;
    }*/

    /**
     * 简历解析并处理头像
     *
     * @param inputStream 简历文件流
     * @param index       文件下标
     * @param channel     渠道名称
     */
    /*public static Map<String, Object> parseResumeAndHead(InputStream inputStream, String fileName, Integer index, String channel) throws Exception {
        //获取到解析结果
        Map<String, Object> parseResult = parseResume(inputStream, fileName, index, channel);
        //处理头像
        PersonalInfo personalInfo = (PersonalInfo) parseResult.get(Constants.ResumeConstants.RESUME_PERSON_INFO);
        String parseHead = personalInfo.getPhotoAddr();
        if (StringUtils.isNotBlank(parseHead)) {
            String ossFileKey = Constants.CommonConstants.HEAD_IMG_OSS_PATH + personalInfo.getPersonChineseName() + "_" + personalInfo.getPersonTel() + ".png";
            personalInfo.setPhotoAddr(ossFileKey);
            if (parseHead.startsWith("http://") || parseHead.startsWith("https://")) {
                //判断为http链接的头像
                try (InputStream headInputStream = ImageUtils.getImageFromHttp(parseHead)) {
                    OSSUtils.uploadFile(ossFileKey, headInputStream);
                } catch (Exception e) {
                    logger.error("获取Http链接头像", e);
                    e.printStackTrace();
                }
            } else if (parseHead.startsWith("data:image/png;base64,")) {
                //判断为base64的头像
                try (InputStream headInputStream = ImageUtils.base64ToImage(parseHead.substring(parseHead.indexOf(",") + 1))) {
                    OSSUtils.uploadFile(ossFileKey, headInputStream);
                } catch (Exception e) {
                    logger.error("转换Base64头像", e);
                    e.printStackTrace();
                }
            } else {
                //不知道是啥了,如果长度小于数据库设置的长度，则直接原样入库，否则丢弃
                if (parseHead.length() <= 300) {
                    personalInfo.setPhotoAddr(parseHead);
                } else {
                    personalInfo.setPhotoAddr(null);
                    logger.warn(Constants.CommonConstants.BASE_LOG, "添加简历头像处理", "未知头像格式，并且长度超过设置长度");
                }
            }
        }
        //重新设置数据,并返回结果
        parseResult.put(Constants.ResumeConstants.RESUME_PERSON_INFO, personalInfo);
        return parseResult;
    }*/

    /**
     * 判断头像为http链接还是base64
     *
     * @param head 头像
     * @return <img src="这里就是返回的结果"/>
     */
  /*  private String getHeadImgSrc(String head) {
        String base64 = "data:image/png;base64,";
        if (StringUtils.isBlank(head)) {
            return base64 + Constants.HtmlConstants.DEFAULT_HEAD_IMG_BASE64;
        } else if (head.startsWith("http://") || head.startsWith("https://")) {
            return head;
        } else if (head.startsWith(base64)) {
            return base64 + head;
        } else {
            return head;
        }
    }*/

    /**
     * 提取补充信息
     *
     * @param supplementRequest 补充信息request
     * @param channel           渠道名称
     */
   /* public static Map<String, Object> extractSupplement(CandidateSupplementRequest supplementRequest, String channel) throws Exception {
        *//*--------------------------------初始化返回对象和基本信息--------------------------------*//*
        Map<String, Object> resultMap = new TreeMap<>();
        //文件标识  - 补充信息
        resultMap.put(Constants.ResumeConstants.RESUME_FILE_FLAG, 1);
        //文件下标
        resultMap.put(Constants.ResumeConstants.RESUME_FILE_INDEX, supplementRequest.getIndex());
        //PDF文件Key
        resultMap.put(Constants.ResumeConstants.RESUME_FILE_KEY, supplementRequest.getFileKey());
        *//*------------------------------------处理个人信息-------------------------------------*//*
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setPersonChineseName(supplementRequest.getName());
        personalInfo.setPersonTel(supplementRequest.getMobile());
        personalInfo.setPersonEmail(supplementRequest.getEmail());
        personalInfo.setPersonSex(supplementRequest.getGender());
        personalInfo.setPersonAge(supplementRequest.getAge());
        personalInfo.setWordYears(supplementRequest.getJobExp());
        personalInfo.setInitialWorkTime((Calendar.getInstance().get(Calendar.YEAR) - NumberUtils.toInt(supplementRequest.getJobExp())) + "");
        personalInfo.setMaxDegree(String.valueOf(supplementRequest.getEducation()));
        personalInfo.setPresentAddrProvince(String.valueOf(supplementRequest.getPresentProvinceId()));
        personalInfo.setPresentAddrCity(String.valueOf(supplementRequest.getPresentCityId()));
        personalInfo.setPresentAddrArea(String.valueOf(supplementRequest.getPresentAreaId()));
        personalInfo.setPresentAddr(supplementRequest.getPresentStr());
        resultMap.put(Constants.ResumeConstants.RESUME_PERSON_INFO, personalInfo);
        *//*------------------------------------处理教育经历-------------------------------------*//*
        Education education = new Education();
        education.setSchoolName(supplementRequest.getSchoolName());
        education.setMajorName(supplementRequest.getSchoolDepartmentName());
        resultMap.put(Constants.ResumeConstants.RESUME_EDUCATION_LIST, Collections.singletonList(education));
        *//*------------------------------------处理工作经历-------------------------------------*//*
        WorkExperience workExperience = new WorkExperience();
        workExperience.setCompanyName(supplementRequest.getLastJobbingCompanyName());
        resultMap.put(Constants.ResumeConstants.RESUME_WORK_EXPERIENCE_LIST, Collections.singletonList(workExperience));
        *//*------------------------------------处理求职意向-------------------------------------*//*
        DesiredPosition desiredPosition = new DesiredPosition();
        desiredPosition.setSalaryLow(supplementRequest.getSalaryLow());
        desiredPosition.setSalaryHigh(supplementRequest.getSalaryHigh());
        resultMap.put(Constants.ResumeConstants.RESUME_DESIRED, desiredPosition);
        return resultMap;
    }*/

    /**
     * 生成pdf文件的唯一ID
     */
    public static String createFileRandomId() {
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Random r = new Random();
        StringBuilder shortBuffer = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 4; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        //获取当前时间
        LocalDate now = LocalDate.now();
        return "PDF" + now.toString().replace("-", "").substring(2) + shortBuffer.toString() + (r.nextInt(899) + 100);
    }

}
