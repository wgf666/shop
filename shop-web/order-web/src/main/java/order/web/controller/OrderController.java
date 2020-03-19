package order.web.controller;

import com.google.gson.Gson;
import constant.RedisConstant;
import entity.*;
import order.web.feign.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author:吴小富
 * @Date: 2020/3/12 21:40
 */
@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

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
        System.out.println(list.get(0).getGoodsPriceOff());

        //Double goodsPriceOff = list.get(0).getGoodsPriceOff();
        String goodsPriceOff = String.format("%.2f", list.get(0).getGoodsPriceOff());
        DecimalFormat fnum  =   new  DecimalFormat("##0.00");

        String dd=fnum.format(Float.parseFloat(sum)*Float.parseFloat(goodsPriceOff));
        map.put("addressList",addressList);
        map.put("list",list);
        map.put("sum",Integer.parseInt(sum));
        map.put("paysum",dd);

        return "pay";
    }

    @RequestMapping("add")
    @ResponseBody
    public String add(String goodsid,String goodsnum, String goodsTotalPrice,TOrder order, ModelMap map){
        //Gson gson = new Gson();
        //String s = gson.toJson(order);
        order.setoOrderdate(new Date());
        order.setUserid(37);
        order.setoStatus("init");
        orderService.add(goodsid,goodsnum,goodsTotalPrice,order);
        map.put("order",order);
        map.put("total",goodsTotalPrice);
        return "success";
    }

    @RequestMapping("success")
    public String success(){
        return "success";
    }
}
