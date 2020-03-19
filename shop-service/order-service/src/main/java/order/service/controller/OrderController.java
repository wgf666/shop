package order.service.controller;

import constant.RedisConstant;
import entity.TGoodsInfo;
import entity.TOrder;
import entity.TOrderDetail;
import order.service.service.IOrderDetailService;
import order.service.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author:吴小富
 * @Date: 2020/3/19 18:35
 */
@Controller
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("order/add")
    @ResponseBody
    public void add(@RequestParam("goodsid") String goodsId, @RequestParam("goodsnum") String sum, @RequestParam("goodsTotalPrice") String total, @RequestBody TOrder order){
        //System.out.println(order.getoShaddress());
        //System.out.println(ord);
        Integer orderId = orderService.addOrder(order);
        //回调主键
        //System.out.println(order.getId());
        if (orderId>0){
            List<TGoodsInfo> goodsInfoList = (List<TGoodsInfo>) redisTemplate.opsForValue().get(RedisConstant.REDIS_INFO);
            TOrderDetail orderDetail = new TOrderDetail();
            for (TGoodsInfo goodsInfo : goodsInfoList) {
                if(goodsInfo.getId()==Integer.parseInt(goodsId)){

                    orderDetail.setGoodsid(Integer.parseInt(goodsId));
                    orderDetail.setGoodsnum(Integer.parseInt(sum));
                    orderDetail.setGoodsTotalPrice(Double.parseDouble(total));
                    orderDetail.setoOrderid(order.getId());
                    orderDetail.setGoodsname(goodsInfo.getGoodsName());
                    orderDetail.setGoodsprice(goodsInfo.getGoodsPriceOff());
                    orderDetail.setGoodsDescription(goodsInfo.getGoodsDescription());
                    orderDetail.setGoodspic(goodsInfo.getGoodsPic());
                    orderDetail.setGoodsDate(new Date());
                }
            }

            orderDetailService.addOrderDetail(orderDetail);

        }else {
            System.out.println("订单生成回调主键失败");
        }

    }
}
