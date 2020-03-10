package search.service;

import entity.TGoodsInfo;
import entity.TGoodsType;
import mapper.TGoodsInfoMapper;
import mapper.TGoodsTypeMapper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("mapper")
public class SearchServiceApplicationTests {

    @Autowired
    private TGoodsInfoMapper goodsInfoMapper;

    @Autowired
    private SolrClient solrClient;

    @Test
    public void contextLoads() {

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
