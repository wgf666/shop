package order.web.controller;

import constant.RedisConstant;
import entity.ResultBean;
import entity.TAddress;
import entity.TGoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:吴小富
 * @Date: 2020/3/12 21:40
 */
@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("desc/{id}")
    public String addOrder(@PathVariable("id") String id, String sum, ModelMap map){
        List<TGoodsInfo> list=new ArrayList<>();
        List<TGoodsInfo> goodsInfoList = (List<TGoodsInfo>) redisTemplate.opsForValue().get(RedisConstant.REDIS_INFO);
        List<TAddress> addressList = (List<TAddress>) redisTemplate.opsForValue().get(RedisConstant.REDIS_ADDRESS);
        for (TGoodsInfo goodsInfo : goodsInfoList) {
            if(goodsInfo.getId()==Integer.parseInt(id)){
                list.add(goodsInfo);
            }
        }

        Double goodsPriceOff = list.get(0).getGoodsPriceOff();
        //System.out.println(id+"------"+sum);
        map.put("addressList",addressList);
        map.put("list",list);
        map.put("sum",Integer.parseInt(sum));
        map.put("paysum",Integer.parseInt(sum)*goodsPriceOff);

        return "pay";
    }

    @RequestMapping("add")
    public String add(){
        return "";
    }
}
