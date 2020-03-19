package index.service;

import constant.RedisConstant;
import entity.TAddress;
import mapper.TAddressMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexServiceApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TAddressMapper addressMapper;

    @Test
    public void contextLoads() {
        //redisTemplate.opsForValue().set("info","");
        redisTemplate.delete("info");
        redisTemplate.delete("address");
    }



}
