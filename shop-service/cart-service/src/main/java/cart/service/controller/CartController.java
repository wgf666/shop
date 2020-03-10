package cart.service.controller;

import cart.service.pojo.CartItem;
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

    @RequestMapping("cart/{key}")
    ResultBean getShopcartList(@PathVariable(value = "key") String key){

        return shopCartService.getShopcartList(key);
    }
    @RequestMapping("cart/add/{key}/{cartItem}")
    ResultBean  add(@PathVariable String key, @PathVariable CartItem cartItem){

        return shopCartService.add(key,cartItem);
    }
}
