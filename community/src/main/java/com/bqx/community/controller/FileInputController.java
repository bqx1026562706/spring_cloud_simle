package com.bqx.community.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.bqx.community.pojo.ExcleImportCityRequest;
import com.bqx.community.pojo.ExcleImportInfo;
import com.bqx.community.pojo.MultiLineHeadExcelModel;
import com.bqx.community.pojo.vo.ServiceResponse;
import com.bqx.community.service.UserService;
import com.bqx.community.utils.ResponseResult;
import com.bqx.community.utils.ResultStatusEnum;
import com.bqx.community.utils.excel.ExcelListener;
import com.bqx.community.utils.excel.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file")
public class FileInputController {

    private final static Logger logger = LoggerFactory.getLogger(FileInputController.class);

    @Autowired
   private UserService userService;

    @RequestMapping(value = "readCityExcel", method = RequestMethod.POST)
    public String readCityExcel(@RequestParam("file") MultipartFile excel) throws IOException {
        InputStream inputStream = excel.getInputStream();


     try{
        //读取第一页 跳过第一条 文字头
        Sheet sheet = new Sheet(1, 1, ExcleImportCityRequest.class);
        List<Object> read = EasyExcelFactory.read(inputStream, sheet);
        List<ExcleImportCityRequest> list = new ArrayList<ExcleImportCityRequest>();
        for (Object obj : read) {
            list.add((ExcleImportCityRequest) obj);
        }
         System.out.println("总体 长度："+list.size());
        //开始下标
        int startIndex = 0;
        //每次入库的条数
        int size = 100;
        boolean end = false;
         List<ExcleImportCityRequest> importlist;
         while (true){
            if (startIndex + size >= list.size()) {
                importlist = list.subList(startIndex, list.size());
                end = true;
            } else {
                importlist = list.subList(startIndex, startIndex + size);
                startIndex = startIndex + size;
            }
             System.out.println("入库时的list 长度："+importlist.size());
        //     ServerResponse serverResponse = userService.impltreadCityExcel(importlist);
            if (end) { break; }
        }


            // 取出数据
            StringBuilder str = new StringBuilder();
            str.append("{");
            String link = "";
            for (int i = 0; i < 100; i++) {
                Integer id = list.get(i).getId();
                String name2 = list.get(i).getName();
                str.append(link).append("\"" + id+ "\":").append("\"" + name2 + "\"");
                link = ",";
            }
            str.append("};");
            System.out.println(str);

    }catch (Exception e){

    }finally {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
        return "读取成功";
    }











    /**
     * 同步读取
     * @param excel
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "readExcel", method = RequestMethod.POST)
    public String readExcel(@RequestParam("file") MultipartFile excel) throws IOException {

        InputStream inputStream = excel.getInputStream();
        /*BufferedInputStream bis = new BufferedInputStream(inputStream);
        ExcelListener listener = new ExcelListener();
        ExcelReader excelReader = new ExcelReader(bis, ExcelTypeEnum.XLS, listener);
        excelReader.read(new Sheet(1, 1, ExcleImportInfo.class));
        //获取项目ID不为空的对象
        List<Object> positionEntityList = listener.getDatas().stream().filter(object -> {
            ExcleImportInfo entity = (ExcleImportInfo) object;
            return !ObjectUtils.isEmpty(entity.getAge()) && !entity.getPhone().equals(0);
        }).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(positionEntityList)) {
            logger.error("导入数据为空！");
        } else {
            //读取完excle 入库  一系列的逻辑判断

        }*/

//        sheetNo --> 读取哪一个 表单
//         headLineMun --> 从哪一行开始读取( 不包括定义的这一行，比如 headLineMun为2 ，那么取出来的数据是从 第三行的数据开始读取 )
//         clazz --> 将读取的数据，转化成对应的实体，需要 extends BaseRowModel
        Sheet sheet = new Sheet(1, 1, ExcleImportInfo.class);
        List<Object> read = EasyExcelFactory.read(inputStream, sheet);
        List<ExcleImportInfo> list = new ArrayList<ExcleImportInfo>();
        for (Object obj : read) {
            list.add((ExcleImportInfo) obj);
        }
      //  ServiceResponse serverResponse =  userService.insertUserExcle(list);

        // 取出数据
        StringBuilder str = new StringBuilder();
        str.append("{");
        String link = "";
        for (ExcleImportInfo mode : list) {
            str.append(link).append("\"" + mode.getPhone() + "\":").append("\"" + mode.getEmail() + "\"");
            link = ",";
        }
        str.append("};");
        System.out.println(str);
        return "读取成功";
    }

    /**
     * 异步读取excle
     * @param
     * @throws IOException
     */
    @RequestMapping(value = "/readAsynchronousExcel",method =RequestMethod.POST)
    public String readAsynchronousExcel(@RequestParam("file") MultipartFile excel) throws IOException {
        InputStream inputStream = excel.getInputStream();
        Sheet sheet = new Sheet(1,1,ExcleImportInfo.class);
        EasyExcelFactory.readBySax(inputStream,sheet,new ExcelListener());
        //这里能读出来，是因为在ExcelListener 中 初始化的时候，存放进去
        List<ExcleImportInfo> readAsynchronousExcelList = ExcelListener.getReadAsynchronousExcelList();
       // ServiceResponse serverResponse =  userService.insertUserExcle(readAsynchronousExcelList);

        return "异步读取成功";
    }


    /**
     * 下载
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "writeExcel", method = RequestMethod.GET)
    public void writeExcel(HttpServletResponse response) throws IOException {
        ServletOutputStream out = null;
        out = response.getOutputStream();
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        String fileName = "excle列表";
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
        //  List<ExcleImportInfo> list = getList();
        List<MultiLineHeadExcelModel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MultiLineHeadExcelModel multiLineHeadExcelModel = new MultiLineHeadExcelModel();
            multiLineHeadExcelModel.setP1(String.valueOf(i));
            multiLineHeadExcelModel.setP2(String.valueOf(i));
            multiLineHeadExcelModel.setP3(i);
            multiLineHeadExcelModel.setP4(123456);
            list.add(multiLineHeadExcelModel);
        }
        ExcelUtil.writeExcel(out, MultiLineHeadExcelModel.class, list);

        // ExcelUtil.writeExcel(out,ExcleImportInfo.class,list);
    }


    private List<ExcleImportInfo> getList() {
        List<ExcleImportInfo> objects = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExcleImportInfo excleImportInfo = new ExcleImportInfo();
            excleImportInfo.setAge("16");
            excleImportInfo.setEmail("123@qq.com");
            excleImportInfo.setName("名字");
            objects.add(excleImportInfo);
        }

        return objects;
    }
}
