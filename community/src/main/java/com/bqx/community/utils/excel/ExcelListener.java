package com.bqx.community.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelListener extends AnalysisEventListener {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;


    //失败信息集合
    private static List<Map<String, String>> mapList = new ArrayList<>();

    //成功条数
    private static Integer curess = 0;
    //失败条数
    private static Integer notcure = 0;
    //异常错误
    private Integer error = 0;

    //自定义用于暂时存储data。
    //可以通过实例获取该值
    private List<Object> datas = new ArrayList<Object>();

    //后台导入用来存储解析好的数据
   // private static List<ExcelBackImport> excelBackList = new ArrayList<>();
    private boolean black;




    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        //数据存储到list，供批量处理，或后续自己业务逻辑处理。
        datas.add(o);
        System.out.println("解析到一条数据:{ "+ o.toString() +" }");

        curess ++;
        if (datas.size() >= BATCH_COUNT) {
            doSomething( curess );
            datas.clear();
        }
        //根据自己业务做处理
       /* if (black) {
            blackDoSomething(o, analysisContext.getCurrentRowNum());
        } else {
            doSomething(o, analysisContext.getCurrentRowNum());
        }*/
    }
    //这里是初始化的时候 ，做一些事情，比如更新 excle 中的数据到es ，solr 等等
    private void doSomething(Integer curess){
        System.out.println("{ "+ curess +" }条数据，开始存储数据库！" + datas.size());

        System.out.println("存储数据库成功！");

    }

    private  void  blackDoSomething(Object object, Integer d){}



    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        datas.clear();//解析结束销毁不用的资源
        System.out.println("所有数据解析完成！");
        System.out.println(" curess ：" + curess);
    }



    public static List<Map<String, String>> getMapList() {
        return mapList;
    }

    public static void setMapList(List<Map<String, String>> mapList) {
        ExcelListener.mapList = mapList;
    }

    public static Integer getCuress() {
        return curess;
    }

    public static void setCuress(Integer curess) {
        ExcelListener.curess = curess;
    }

    public static Integer getNotcure() {
        return notcure;
    }

    public static void setNotcure(Integer notcure) {
        ExcelListener.notcure = notcure;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }

    public boolean isBlack() {
        return black;
    }

    public void setBlack(boolean black) {
        this.black = black;
    }
}
