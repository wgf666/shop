package index.service.service.impl;

import constant.RedisConstant;
import dto.ResultBean;
import entity.TGoodsType;
import index.service.service.IGoodsTypeService;
import mapper.TGoodsTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author:吴小富
 * @Date: 2020/3/11 12:34
 */
@Service
public class IGoodsTypeServiceImpl implements IGoodsTypeService {

    @Autowired
    private TGoodsTypeMapper goodsTypeMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResultBean selectAll() {

        List<TGoodsType> goodsTypeList = (List<TGoodsType>) redisTemplate.opsForValue().get(RedisConstant.REDIS_TYPE);

        if(goodsTypeList==null){
            goodsTypeList = goodsTypeMapper.selectAll();
            redisTemplate.opsForValue().set(RedisConstant.REDIS_TYPE,goodsTypeList,RedisConstant.SESSION_TIMEOUT, TimeUnit.SECONDS);
        }
        return ResultBean.success(goodsTypeList);
    }
}
