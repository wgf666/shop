package cart.web.serivce;

import dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiejiawei
 * @Date 2020/3/10
 * @Time 20:30
 */
@FeignClient(value = "CART-SERVICE")

public interface IShopCartService {

    @RequestMapping("user/cart/{key}")
    ResultBean showCart(@PathVariable(value = "key") String key);

    @RequestMapping("user/cart/add/{key}/{goodsId}/{count}")
    ResultBean  addProduct(@PathVariable(value = "key") String key,
                           @PathVariable(value = "goodsId") long goodsId,
                            @PathVariable(value = "count") Integer count);

    @RequestMapping("user/cart/clean/{key}/{productId}")
    ResultBean clean(@PathVariable(value = "key") String key,
                     @PathVariable(value = "productId") Long productId);

    @RequestMapping("user/cart/update/{uuid}/{productId}/{count}")
    ResultBean update(@PathVariable(value = "uuid") String uuid,
                      @PathVariable(value = "productId") Long productId,
                      @PathVariable(value = "count") Integer count);

    @RequestMapping("user/cart/merge/{noLoginKey}/{loginKey}")
    ResultBean merge(@PathVariable(value = "noLoginKey") String noLoginKey,
                     @PathVariable(value = "loginKey") String loginKey);
}
