package cart.web.controller;

import cart.web.serivce.IShopCartService;
import dto.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiejiawei
 * @Date 2020/3/10
 * @Time 19:51
 */
@RestController
@RequestMapping("user")
public class ShopCartController {

    @Autowired
    private IShopCartService shopCartService;

    @RequestMapping("cart/{key}")
    ResultBean showCart(@PathVariable(value = "key") String key){

        return shopCartService.showCart(key);
    }
    @RequestMapping("cart/add/{key}/{goodsId}/{count}")
    ResultBean  addProduct(@PathVariable(value = "key") String key,
                           @PathVariable(value = "goodsId") long goodsId,
                            @PathVariable(value = "count") Integer count){

        return shopCartService.addProduct(key,goodsId,count);
    }
    @RequestMapping("cart/clean/{key}/{productId}")
    ResultBean clean(@PathVariable(value = "key") String key,
                     @PathVariable(value = "productId") Long productId){
        return shopCartService.clean(key,productId);
    }

    @RequestMapping("cart/update/{uuid}/{productId}/{count}")
    ResultBean update(@PathVariable(value = "uuid") String uuid,
                      @PathVariable(value = "productId") Long productId,
                      @PathVariable(value = "count") Integer count){
        return shopCartService.update(uuid,productId,count);
    }

    @RequestMapping("cart/merge/{noLoginKey}/{loginKey}")
    ResultBean merge(@PathVariable(value = "noLoginKey") String noLoginKey,
                     @PathVariable(value = "loginKey") String loginKey){
        return shopCartService.merge(noLoginKey,loginKey);
    }

}
