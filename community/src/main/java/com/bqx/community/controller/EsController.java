package com.bqx.community.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bqx.community.pojo.GoodSBean;
import com.bqx.community.utils.HttpClientUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Es api 使用
 */
@RequestMapping("/es")
@RestController
public class EsController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    /**
     * 爬取数据
     * @return
     */
    @RequestMapping("/crawler")
    public  Boolean crawler(){
        try {
            String url="https://search-x.jd.com/Search";
            HashMap<String, Object> parames = new HashMap<>();
            parames.put("callback","goods");
            parames.put("area",27);

            parames.put("ad_ids","291:33");
            parames.put("xtest","new_search");
            String postJson = HttpClientUtil.get(url, parames);
            String substring = postJson.substring(6, postJson.length() - 1);
            JSONObject jsonObject = JSON.parseObject(substring);
            String string = jsonObject.getString("291");
            List<GoodSBean> goodSBeans= JSON.parseArray(string, GoodSBean.class);

            boolean b = elasticsearchTemplate.indexExists(GoodSBean.class);
            if (!b){
                //不存在 创建
                elasticsearchTemplate.createIndex(GoodSBean.class);
            }
            ArrayList<IndexQuery> indexQueries = new ArrayList<>();
            for (GoodSBean goodSBean : goodSBeans) {
                String suuid = UUID.randomUUID().toString().replaceAll("-", " ");

                goodSBean.setId(Integer.parseInt(suuid));
                IndexQuery indexQuery = new IndexQueryBuilder()
                        .withId(suuid)
                        .withObject(goodSBean)
                        .build();
                indexQueries.add(indexQuery);
            }
            elasticsearchTemplate.bulkIndex(indexQueries);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }

    }

    @RequestMapping("esquery/electricity")
    public  String electricity(){
        BoolQueryBuilder boolQueryBuilder =new BoolQueryBuilder();

      //  boolQueryBuilder.filter()

        return  "";
    }



}
