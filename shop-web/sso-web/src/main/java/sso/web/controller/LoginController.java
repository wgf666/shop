package sso.web.controller;

import constant.CookieConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author xiejiawei
 * @Date 2020/3/12
 * @Time 22:29
 */
public class LoginController {

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("checkLogin")
    public String checkLogin(String uname, String password, HttpServletResponse response,
                             @CookieValue(name = CookieConstant.USER_CART,required = false)String userCartUuid){

        //交给service去验证用户名和密码是否正确
        /*ResultBean resultBean = userService.checkLogin(uname,password);
        if(resultBean.getErrno()==0){
            //登录成功
            //生成cookie
            String uuid = UUID.randomUUID().toString();
            Cookie cookie = new Cookie(CookieConstant.USER_LOGIN,uuid);
            //往redis里存
            //组织键
            String key = util.StringUtil.getRedisKey(RedisConstant.USER_LOGIN_PRE, uuid);
            //把登录成功后的用户对象存入到redis中。以便checkIsLogin接口去判断是否已登录 来使用
            redisTemplate.opsForValue().set(key,resultBean.getData(),30, TimeUnit.DAYS);
            //cookie要发送给客户端
            cookie.setMaxAge(30*24*60*60);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);

            //===========合并购物车的Http请求===========
            //因为使用HttpClient来发送请求，所以Cookie不能像浏览器发送请求一样自动携带，因此需要我们手动携带Cookie
            //Cookie: user_cart=3b0c227d-9616-4098-b9f3-71cd1405150e; user_login=92cd6c37-36ea-426c-8a72-a826ae9ef579
            String url = "http://localhost:9085/cart/merge";
            StringBuilder sb = new StringBuilder();
            //未登录的时候如果添加了购物车，那么cookie里就会有这个uuid
            sb.append(CookieConstant.USER_CART);
            sb.append("=");
            sb.append(userCartUuid);
            sb.append(";");
            //如果已经登录了，那么cookie里就会有这个user_login的uuid
            sb.append(CookieConstant.USER_LOGIN);
            sb.append("=");
            sb.append(uuid);
            HttpClientUtils.doGet(url, sb.toString());


            return "redirect:http://localhost:9082";

        }*/

        return "redirect:showLogin";
    }

}
