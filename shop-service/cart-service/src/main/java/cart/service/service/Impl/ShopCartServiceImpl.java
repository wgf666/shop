package cart.service.service.Impl;

import cart.service.pojo.CartItem;
import cart.service.service.ShopCartService;
import cart.service.vo.CartItemVO;
import dto.ResultBean;
import entity.TGoodsInfo;
import mapper.TGoodsInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author xiejiawei
 * @Date 2020/3/9
 * @Time 21:17
 */
@Service
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired
    private TGoodsInfoMapper goodsInfoMapper;

    @Autowired
    private RedisTemplate redisTemplate;



    /**
     * 获取购物车集合
     * @param key
     * @return
     */
    @Override
    public ResultBean showCart(String key) {
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
            TGoodsInfo goodsInfo = (TGoodsInfo) redisTemplate.opsForValue().get(stringBuilder.toString());
            if (goodsInfo == null) {
                //查询数据库, 说明是热门商品
                goodsInfo = goodsInfoMapper.selectByPrimaryKey(cartItem.getProductId().intValue());
                //缓存以下，设置有效期
                redisTemplate.opsForValue().set(stringBuilder.toString(), goodsInfo);
                redisTemplate.expire(stringBuilder.toString(), 60, TimeUnit.MINUTES);
            }
            vo.setGoodsInfo(goodsInfo);
            cartItemVOS.add(vo);
        }
        //cartItemVOS-->
        Collections.sort(cartItemVOS, (o1, o2) -> (int) (o2.getUpdateTime().getTime()-o1.getUpdateTime().getTime()));

        return new ResultBean(200,cartItemVOS,"成功");
    }

    @Override
    public ResultBean merge(String noLoginKey, String loginKey) {
        //1.获取未登录购物车
        List<CartItem> noLoginCart = (List<CartItem>) redisTemplate.opsForValue().get(noLoginKey);
        if(noLoginCart == null){
            return ResultBean.success("不存在未登录购物车，无需合并！");
        }
        //2.获取已登录购物车
        List<CartItem> loginCart = (List<CartItem>) redisTemplate.opsForValue().get(loginKey);
        if(loginCart == null){
            //不存在已登录购物车
            redisTemplate.opsForValue().set(loginKey,noLoginCart);
            return resetToReids(loginKey,noLoginCart);
        }
        //3.两者都存在，需要合并两辆购物车
        //hashMap key productId--->cartItem
        HashMap<Long,CartItem> map = new HashMap<>();
        for (CartItem cartItem : noLoginCart) {
            map.put(cartItem.getProductId(),cartItem);
        }
        //
        for (CartItem cartItem : loginCart) {
            CartItem item = map.get(cartItem.getProductId());
            if(item == null){
                map.put(cartItem.getProductId(),cartItem);
            }else{
                item.setCount(cartItem.getCount()+item.getCount());
            }
        }
        //hashMap--->list
        Collection<CartItem> values = map.values();
        List<CartItem> list = new ArrayList<>(values);
        //将list写到已登录购物车
        ResultBean resultBean = resetToReids(loginKey, list);
        //删除未登录购物车
        redisTemplate.delete(noLoginKey);
        return resultBean;
    }

    /**
     * 添加购物车
     * @param key
     * @param goodsId
     * @return
     */
    @Override
    public ResultBean addProduct(String key, Long goodsId,Integer count) {
        //根据key来寻找购物车
        List<CartItem> cart = (List<CartItem>) redisTemplate.opsForValue().get(key);
        TGoodsInfo tGoodsInfo = goodsInfoMapper.selectByPrimaryKey(goodsId.intValue());

        //购物车不存在
        if(cart==null){
            cart = new ArrayList<>();
            cart.add(new CartItem(tGoodsInfo.getId().longValue(),count,new Date()));
            //保存在redis中 刷新有效期
            return resetToReids(key, cart);

        }
        //购物车存在
        // 遍历查看当前添加的商品是否在购物车里面
        for (CartItem cartItem : cart) {
            //longValue()：将包装类转化为基本类型
            if(cartItem.getProductId().longValue()==tGoodsInfo.getId().longValue()){
                //商品已存在的情况
                cartItem.setCount(cartItem.getCount()+count);
                cartItem.setUpdateTime(new Date());
                //保存到redis中,刷新有效期
                return resetToReids(key, cart);
            }
        }
        //商品不存在购物车中
        cart.add(new CartItem(goodsId,count,new Date()));
        //保存到redis中,刷新有效期
        return resetToReids(key, cart);
    }

    @Override
    public ResultBean clean(String key, Long productId) {
        //1.获取到购物车
        List<CartItem> carts = (List<CartItem>) redisTemplate.opsForValue().get(key);
        if(carts != null){
            for (CartItem cartItem : carts) {
                if(productId.longValue() == cartItem.getProductId().longValue()){
                    carts.remove(cartItem);
                    //
                    return resetToReids(key, carts);
                }
            }
        }
        return ResultBean.error("删除失败！");
    }

    @Override
    public ResultBean update(String key, Long productId, Integer count) {
        //1.获取购物车
        List<CartItem> carts = (List<CartItem>) redisTemplate.opsForValue().get(key);
        if(carts != null){
            //2.遍历更新数量
            for (CartItem cartItem : carts) {
                if(productId.longValue() == cartItem.getProductId().longValue()){
                    cartItem.setCount(count);
                    cartItem.setUpdateTime(new Date());
                    //写会redis
                    return resetToReids(key, carts);
                }
            }
        }
        return ResultBean.error("更新失败！");
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

