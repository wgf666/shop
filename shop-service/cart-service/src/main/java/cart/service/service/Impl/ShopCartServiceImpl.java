package cart.service.service.Impl;

import cart.service.pojo.CartItem;
import cart.service.service.ShopCartService;
import cart.service.vo.CartItemVO;
import dto.ResultBean;
import entity.TProduct;
import mapper.TProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xiejiawei
 * @Date 2020/3/9
 * @Time 21:17
 */
@Service
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired
    private TProductMapper productMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 获取购物车集合
     * @param key
     * @return
     */
    @Override
    public ResultBean getShopcartList(String key) {
        List<CartItem> cartItems = (List<CartItem>) redisTemplate.opsForValue().get(key);
            if (cartItems == null) {
            return ResultBean.error("您当前购物车还没有添加商品信息!");
        }

        List<CartItemVO> cartItemVOS = new ArrayList<>(cartItems.size());
        for (CartItem cartItem : cartItems) {
            CartItemVO vo = new CartItemVO();
            vo.setCount(cartItem.getCount());
            vo.setUpdateTime(cartItem.getUpdateTime());
            //缓存这里发挥作用
            //前提条件：系统提前做好热门商品的预热，根据商品的访问量，将20%的商品进行缓存
            //key----------value
            //product:101---productInfo
            StringBuilder stringBuilder = new StringBuilder("product:").append(cartItem.getProductId());
            TProduct product = (TProduct) redisTemplate.opsForValue().get(stringBuilder.toString());
            if (product == null) {
                //查询数据库, 说明不是热门商品
                product = productMapper.selectByPrimaryKey(cartItem.getProductId());
                //缓存以下，设置有效期
                redisTemplate.opsForValue().set(stringBuilder.toString(), product);
                redisTemplate.expire(stringBuilder.toString(), 60, TimeUnit.MINUTES);
            }
            vo.setProduct(product);
            cartItemVOS.add(vo);
        }
       /* //cartItemVOS-->
        Collections.sort(cartItemVOS, new Comparator<CartItemVO>() {
            @Override
            public int compare(CartItemVO o1, CartItemVO o2) {
                return (int) (o2.getUpdateTime().getTime()-o1.getUpdateTime().getTime());
            }
        });*/

        return new ResultBean(200,cartItemVOS,"成功");
    }

    /**
     * 添加购物车
     * @param key
     * @param cartItem
     * @return
     */

    @Override
    public ResultBean add(String key, CartItem cartItem) {
        //根据key来寻找购物车
        List<CartItem> cart = (List<CartItem>) redisTemplate.opsForValue().get(key);

        //购物车不存在
        if(cart==null){
            cart = new ArrayList<>();
            cart.add(new CartItem(cartItem.getProductId(),cartItem.getName(),cartItem.getPrice(),cartItem.getProductDesc()
                                    ,cartItem.getCount(),new Date()));
            //保存在redis中 刷新有效期
            return resetToReids(key, cart);

        }
        //购物车存在
        // 遍历查看当前添加的商品是否在购物车里面
        for (CartItem c : cart) {
            //longValue()：将包装类转化为基本类型
            if(c.getProductId().longValue()==cartItem.getProductId().longValue()){
                //商品已存在的情况
                c.setCount(cartItem.getCount()+cartItem.getCount());
                c.setUpdateTime(new Date());
                //保存到redis中,刷新有效期
                return resetToReids(key, cart);
            }
        }
        //商品不存在购物车中
        cart.add(new CartItem(cartItem.getProductId(),cartItem.getName(),cartItem.getPrice(),cartItem.getProductDesc()
                ,cartItem.getCount(),new Date()));
        //保存到redis中,刷新有效期
        return resetToReids(key, cart);
    }

    /**
     * 保存在redis中
     * @param key
     * @param carts
     * @return
     */
    private ResultBean resetToReids(String key, List<CartItem> carts) {
        redisTemplate.opsForValue().set(key,carts);
        redisTemplate.expire(key,30, TimeUnit.DAYS);
        return ResultBean.success(String.valueOf(carts.size()));
    }
}

