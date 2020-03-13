package cart.service.service;

import dto.ResultBean;

/**
 * @author xiejiawei
 * @Date 2020/3/9
 * @Time 21:17
 */
public interface ShopCartService {


    ResultBean addProduct(String key, Long productId, Integer count);

    ResultBean clean(String key, Long productId);

    ResultBean update(String uuid, Long productId, Integer count);

    ResultBean showCart(String uuid);

    ResultBean merge(String noLoginKey, String loginKey);

}
