package cart.web.controller;

import cart.web.pojo.CartItem;
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
    ResultBean getShopcartList(@PathVariable(value = "key") String key){

        return shopCartService.getShopcartList(key);
    }

    @RequestMapping("user/cart/add/{key}/{cartItem}")
    ResultBean  add(@PathVariable String key, @PathVariable CartItem cartItem){

        return shopCartService.add(key,cartItem);
    }

}
