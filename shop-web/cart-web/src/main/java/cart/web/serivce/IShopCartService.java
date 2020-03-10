package cart.web.serivce;

import cart.web.pojo.CartItem;
import dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiejiawei
 * @Date 2020/3/10
 * @Time 20:30
 */
@FeignClient(value = "cart-service")
public interface IShopCartService {

    @RequestMapping("user/cart/{key}")
    ResultBean getShopcartList(@PathVariable(value = "key") String key);

    @RequestMapping("user/cart/add/{key}/{cartItem}")
    ResultBean add(@PathVariable String key, @PathVariable CartItem cartItem);
}
