package index.service.service.impl;

import constant.RedisConstant;
import entity.TAddress;
import entity.TGoodsType;
import index.service.service.IAddressService;
import mapper.TAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author:吴小富
 * @Date: 2020/3/17 18:56
 */
@Service
public class IAddressServiceImpl implements IAddressService {

    @Autowired
    private TAddressMapper addressMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void selectAll() {

        List<TAddress> addressList = (List<TAddress>) redisTemplate.opsForValue().get(RedisConstant.REDIS_ADDRESS);

        if(addressList==null){
            addressList = addressMapper.selectAll();
            System.out.println(addressList);
            redisTemplate.opsForValue().set(RedisConstant.REDIS_ADDRESS,addressList,RedisConstant.SESSION_TIMEOUT, TimeUnit.SECONDS);
        }
    }
}
