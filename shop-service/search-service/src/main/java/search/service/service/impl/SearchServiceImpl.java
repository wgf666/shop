package search.service.service.impl;

import dto.ResultBean;
import mapper.TGoodsInfoMapper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import search.service.service.ISearchService;

/**
 * @author:吴小富
 * @Date: 2020/3/10 11:04
 */
@Service
public class SearchServiceImpl implements ISearchService {

    @Autowired
    private TGoodsInfoMapper goodsInfoMapper;

    @Autowired
    private SolrClient solrClient;



    @Override
    public ResultBean searchByKeyword(@RequestParam String keyword) {

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("df","t_item_keywords");

        return null;
    }

    @Override
    public ResultBean addProduct(Long pid) {
        return null;
    }
}
