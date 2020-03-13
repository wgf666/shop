package cart.service.controller;

import cart.service.service.ShopCartService;
import dto.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiejiawei
 * @Date 2020/3/9
 * @Time 21:02
 */
@RestController
@RequestMapping("user")
public class CartController {

    @Autowired
    private ShopCartService shopCartService;

    @RequestMapping("cart/{uuid}")
    ResultBean showCart(@PathVariable(value = "uuid") String uuid){

        return shopCartService.showCart(uuid);
    }
    @RequestMapping("cart/add/{key}/{goodsId}/{count}")
    ResultBean  addProduct(@PathVariable String key,
                           @PathVariable long goodsId,
                            @PathVariable Integer count){

        return shopCartService.addProduct(key,goodsId,count);
    }
    @RequestMapping("cart/clean/{key}/{productId}")
    ResultBean clean(@PathVariable String key,
                     @PathVariable Long productId){
        return shopCartService.clean(key,productId);
    }

    @RequestMapping("cart/update/{uuid}/{productId}/{count}")
    ResultBean update( @PathVariable String uuid,
                       @PathVariable Long productId,
                       @PathVariable Integer count){
        return shopCartService.update(uuid,productId,count);
    }

    @RequestMapping("cart/merge/{noLoginKey}/{loginKey}")
    ResultBean merge(@PathVariable String noLoginKey,
                     @PathVariable String loginKey){
        return shopCartService.merge(noLoginKey,loginKey);
    }
}
