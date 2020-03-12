package cart.web.controller;

import cart.web.serivce.IShopCartService;
import constant.CookieConstant;
import dto.ResultBean;
import entity.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author xiejiawei
 * @Date 2020/3/10
 * @Time 19:51
 */
@Controller
@RequestMapping("user")
public class ShopCartController {

    @Autowired
    private IShopCartService shopCartService;

    @RequestMapping("cart/{key}")
    String showCart(@PathVariable(value = "key") String key, Model model){
        ResultBean resultBean = shopCartService.showCart(key);
        model.addAttribute("result",resultBean.getData());
        return "shopcart";
    }
    @ResponseBody
    @RequestMapping("cart/add/{goodsId}/{count}")
    ResultBean  addProduct(@CookieValue(name = CookieConstant.USER_CART, required = false) String uuid,
                           @PathVariable(value = "goodsId") long goodsId,
                           @PathVariable(value = "count") Integer count,
                           HttpServletRequest request,
                           HttpServletResponse response){

        Object o = request.getAttribute("user");
        if(o!=null){
            //已经登陆状态下的购物车
            TUser user = (TUser) o;
            Long userId = user.getId().longValue();
            return shopCartService.addProduct(userId.toString(),goodsId,count);

        }

        //未登录状态下的购物车
        if(uuid==null || "" .equals(uuid)){
            //创建一个uuid返回给浏览器
            uuid= UUID.randomUUID().toString();
            //返回uuid给cookie
            Cookie cookie =new Cookie(CookieConstant.USER_CART,uuid);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        return shopCartService.addProduct(uuid,goodsId,count);
    }
    @ResponseBody
    @RequestMapping("cart/clean/{key}/{productId}")
    ResultBean clean(@CookieValue(name=CookieConstant.USER_CART,required = false)String uuid,
                     HttpServletResponse response,
                     HttpServletRequest request,
                    @PathVariable(value = "key") String key,
                     @PathVariable(value = "productId") Long productId){
        Object o = request.getAttribute("user");
        if(o!=null){
            //===========已登录状态下的购物车============
            TUser user = (TUser) o;
            return shopCartService.clean(key,productId);
        }
        //===========未登录状态下的购物车===========
        if(uuid!=null&&!"".equals(uuid)){
            //删除Cookie
            Cookie cookie = new Cookie(CookieConstant.USER_CART,"");
            cookie.setMaxAge(0);
            cookie.setPath("/");//admin.qf.com  sso.qf.com  ****.qf.com
            // cookie.setDomain("qf.com");//如果我们使用域名来访问，那么cookie不会被携带，只有这边设置了这个一级域名，qf.com,那么在该域名下的所有二级cookie就都可以携带该cookie
            response.addCookie(cookie);

            //删除redis中的购物车
            return shopCartService.clean(key,productId);
        }
        return ResultBean.error("当前用户没有购物车");
    }

    @ResponseBody
    @RequestMapping("cart/update/{productId}/{count}")
    ResultBean update(@CookieValue(name=CookieConstant.USER_CART,required = false)String uuid,
                      @PathVariable(value = "productId") Long productId,
                      @PathVariable(value = "count") Integer count,

                      HttpServletRequest request){
        Object o = request.getAttribute("user");
        if(o!=null){
            //=============已登录状态下的购物车==============  user:cart:userId
            TUser user = (TUser) o;
            return shopCartService.update(user.getId().toString(),productId,count);

        }
        return shopCartService.update(uuid,productId,count);
    }
    @ResponseBody
    @RequestMapping("cart/merge/{noLoginKey}/{loginKey}")
    ResultBean merge(@PathVariable(value = "noLoginKey") String noLoginKey,
                     @PathVariable(value = "loginKey") String loginKey,
                    @CookieValue(name = CookieConstant.USER_CART,required = false)String uuid,
                     HttpServletRequest request,HttpServletResponse response){
        //获得uuid,和uid
        TUser user = (TUser) request.getAttribute("user");
        String userId = null;
        if(user!=null){
            userId = user.getId().toString();
        }

        Cookie cookie = new Cookie(CookieConstant.USER_CART,"");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);


        return shopCartService.merge(uuid,userId);
    }

}
