package order.web.feign;

import entity.TOrder;
import entity.TOrderDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author:吴小富
 * @Date: 2020/3/19 18:31
 */
@FeignClient("order-service")
public interface IOrderService {

    @RequestMapping(value = "order/add",method= RequestMethod.GET)
    public void add(@RequestParam("goodsid") String goodsId,@RequestParam("goodsnum") String sum, @RequestParam("goodsTotalPrice") String total,@RequestBody TOrder order);
}
