package search.service;

import entity.TGoodsInfo;
import mapper.TGoodsInfoMapper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("mapper")
public class SearchServiceApplication {

    @Autowired
    private static TGoodsInfoMapper goodsInfoMapper;

    @Autowired
    private static SolrClient solrClient;

    public static void main(String[] args) {
        SpringApplication.run(SearchServiceApplication.class, args);

        List<TGoodsInfo> goodsInfoList = goodsInfoMapper.selectAll();

        List<SolrInputDocument> solrList=new ArrayList<>();

        for (TGoodsInfo goodsInfo : goodsInfoList) {
            SolrInputDocument document = new SolrInputDocument();
            document.setField("id",goodsInfo.getId());
            document.setField("t_goods_name",goodsInfo.getGoodsName());
            document.setField("t_goods_price_off",goodsInfo.getGoodsPriceOff());
            document.setField("t_goods_pic",goodsInfo.getGoodsPic());
            document.setField("t_goods_description",goodsInfo.getGoodsDescription());

            solrList.add(document);
        }

        try {
            solrClient.add(solrList);
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
