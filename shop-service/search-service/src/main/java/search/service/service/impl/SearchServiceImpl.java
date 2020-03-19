package search.service.service.impl;

import dto.ResultBean;
import entity.TGoodsInfo;
import mapper.TGoodsInfoMapper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import search.service.service.ISearchService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author:吴小富
 * @Date: 2020/3/10 11:04
 */
@Service
@Component
public class SearchServiceImpl implements ISearchService {

    @Autowired
    private TGoodsInfoMapper goodsInfoMapper;

    @Autowired
    private SolrClient solrClient;



    @Override
    public ResultBean searchByKeyword(@RequestParam String keyword) {
        //初始化solr
        List<TGoodsInfo> goodsInfoList1 = goodsInfoMapper.selectAll();
        List<SolrInputDocument> solrList=new ArrayList<>();
        for (TGoodsInfo goodsInfo : goodsInfoList1) {
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

        //创建查询对象并设置key
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("df","t_goods_keywords");
        solrQuery.setQuery(keyword);

        //分页
        solrQuery.setStart(0);
        solrQuery.setRows(5);

        //开启高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("t_goods_name");
        solrQuery.addHighlightField("t_goods_description");
        solrQuery.setHighlightSimplePre("<span style='color:red'>");
        solrQuery.setHighlightSimplePost("</span>");

        List<TGoodsInfo> goodsInfoList =new ArrayList<>();
        try {
            //执行查询
            QueryResponse query = solrClient.query(solrQuery);
            //取得全部数据集合
            SolrDocumentList results = query.getResults();
            //取得高亮数据集合
            Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();

            for (SolrDocument document : results) {
                TGoodsInfo goodsInfo = new TGoodsInfo();
                //设置id
                String id = (String) document.getFieldValue("id");
                goodsInfo.setId(Integer.parseInt(id));

                //取出id对应得高亮数据
                Map<String, List<String>> stringListMap = highlighting.get(id);
                List<String> list1 = stringListMap.get("t_goods_name");
                List<String> list2 = stringListMap.get("t_goods_description");
                goodsInfo.setGoodsName(list1.get(0));
                goodsInfo.setGoodsDescription(list2.get(0));

                Double t_goods_price_off = (Double) document.getFieldValue("t_goods_price_off");
                goodsInfo.setGoodsPriceOff(t_goods_price_off);
                String t_goods_pic = (String) document.getFieldValue("t_goods_pic");
                goodsInfo.setGoodsPic(t_goods_pic);

                goodsInfoList.add(goodsInfo);
            }

            return ResultBean.success(goodsInfoList);

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultBean.error("无法查询数据，请检查网络");
    }

    @Override
    public void delById(Integer id) {
        try {
            solrClient.deleteById(id.toString());
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delByIds(Integer[] ids) {
        try {
            for (int i = 0; i < ids.length; i++) {
                solrClient.deleteById(ids[i].toString());
            }
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateById(TGoodsInfo goodsInfo) {
        SolrInputDocument document = new SolrInputDocument();
        document.setField("id",goodsInfo.getId());
        document.setField("t_goods_name",goodsInfo.getGoodsName());
        document.setField("t_goods_price_off",goodsInfo.getGoodsPriceOff());
        document.setField("t_goods_pic",goodsInfo.getGoodsPic());
        document.setField("t_goods_description",goodsInfo.getGoodsDescription());
        try {
            solrClient.add(document);
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }
}
