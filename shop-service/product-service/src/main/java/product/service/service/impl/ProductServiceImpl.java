package product.service.service.impl;

import constant.RedisConstant;
import entity.TGoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import product.service.service.IProductService;

import java.util.List;

/**
 * @author:吴小富
 * @Date: 2020/3/13 11:17
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public TGoodsInfo desc(String infoId) {
        List<TGoodsInfo> list = (List<TGoodsInfo>) redisTemplate.opsForValue().get(RedisConstant.REDIS_INFO);
        TGoodsInfo goodsInfo1 = new TGoodsInfo();
        for (TGoodsInfo goodsInfo : list) {
            if(goodsInfo.getId()==Integer.parseInt(infoId)){
                goodsInfo1=goodsInfo;
            }
        }
        return goodsInfo1;
    }
}
