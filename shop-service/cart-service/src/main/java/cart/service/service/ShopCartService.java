package cart.service.service;

import cart.service.pojo.CartItem;
import dto.ResultBean;

/**
 * @author xiejiawei
 * @Date 2020/3/9
 * @Time 21:17
 */
public interface ShopCartService {

    //获取购物车集合
    ResultBean  getShopcartList(String key);

    //添加购物车
    ResultBean  add(String key, CartItem cartItem);

}
