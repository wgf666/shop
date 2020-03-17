package address.service.service.impl;

import address.service.service.IAddressService;
import constant.RedisConstant;
import entity.TAddress;
import mapper.TAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author:吴小富
 * @Date: 2020/3/16 19:27
 */
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private TAddressMapper addressMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void add(TAddress address) {
        int i = addressMapper.insertSelective(address);
        if(i>0){
            List<TAddress> addressList = addressMapper.selectAll();
            redisTemplate.opsForValue().set(RedisConstant.REDIS_ADDRESS,addressList,RedisConstant.SESSION_TIMEOUT, TimeUnit.SECONDS);
        }
    }

    @Override
    public int del(Integer id) {
        return addressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void defaultById(Integer id) {
        addressMapper.updateById(id);
    }
}
