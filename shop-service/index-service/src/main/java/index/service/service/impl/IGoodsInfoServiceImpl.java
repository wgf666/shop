package index.service.service.impl;

import constant.RedisConstant;
import dto.ResultBean;
import entity.TAddress;
import entity.TGoodsInfo;
import entity.TGoodsType;
import index.service.service.IGoodsInfoService;
import index.service.service.IGoodsTypeService;
import mapper.TAddressMapper;
import mapper.TGoodsInfoMapper;
import mapper.TGoodsTypeMapper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author:吴小富
 * @Date: 2020/3/11 12:34
 */
@Service
public class IGoodsInfoServiceImpl implements IGoodsInfoService {

    @Autowired
    private TGoodsInfoMapper goodsInfoMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TAddressMapper addressMapper;

    @Override
    public ResultBean selectAll() {

        List<TGoodsInfo> goodsInfoList = (List<TGoodsInfo>) redisTemplate.opsForValue().get(RedisConstant.REDIS_INFO);
        if(goodsInfoList==null){
            goodsInfoList = goodsInfoMapper.selectAll();
            redisTemplate.opsForValue().set(RedisConstant.REDIS_INFO,goodsInfoList,RedisConstant.SESSION_TIMEOUT, TimeUnit.SECONDS);

        }

            return ResultBean.success(goodsInfoList);
    }

}
