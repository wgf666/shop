package sso.web.controller;


import constant.CookieConstant;
import dto.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sso.web.service.UserService;
import util.HttpClientUtils;
import util.StringUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Controller
//@CrossOrigin(origins = "*", maxAge = 3600,allowCredentials = "true")
public class LoginController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    @RequestMapping("showLogin")
    public String showLogin() {
        System.out.println(1);
      return "login";
    }
    //判断账号是否登录成功，如果成功，存入redis和cookie中
    @RequestMapping("checkLogin")
    public String checkLogin(String uname, String password, HttpServletResponse response,
                             @CookieValue(name = CookieConstant.USER_CART,required = false)String userCartUuid){

        ResultBean resultBean = userService.checkLogin(uname, password);

        if(resultBean.getErrno()==200){
            String uuid = UUID.randomUUID().toString();
            //redis中的key,并且存入reids
            String redisKey = StringUtil.getRedisKey(CookieConstant.USER_LOGIN, uuid);
            redisTemplate.opsForValue().set(redisKey,resultBean.getData(),30, TimeUnit.DAYS);
            //然后组织将uuid存入cookie中拿到redis中的user
            Cookie cookie = new Cookie(CookieConstant.USER_LOGIN, uuid);
            cookie.setMaxAge(30*24*60*60);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);


                //交给service去验证用户名和密码是否正确

            //===========合并购物车的Http请求===========
            //因为使用HttpClient来发送请求，所以Cookie不能像浏览器发送请求一样自动携带，因此需要我们手动携带Cookie
            //Cookie: user_cart=3b0c227d-9616-4098-b9f3-71cd1405150e; user_login=92cd6c37-36ea-426c-8a72-a826ae9ef579
            String url = "http://localhost:9010/user/cart/merge";
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


            return "redirect:http://localhost:9031/index";
        }





        return "redirect:showLogin";
    }
 @RequestMapping("checkIsLogin")
 @ResponseBody
 //判断用户是否已登录
 public  ResultBean checkIsLogin(HttpServletRequest request){
   //要从客户端cookie中拿到cookie
     Cookie[] cookies = request.getCookies();
     for (Cookie cookie : cookies) {
         if(CookieConstant.USER_LOGIN.equals(cookie.getName())){
             String uuid= cookie.getValue();
             String redisKey = StringUtil.getRedisKey(CookieConstant.USER_LOGIN, uuid);
             Object o = redisTemplate.opsForValue().get(redisKey);
             if(o!=null){
                 return ResultBean.success(o,"用户已登录");
             }
         }
     }
     return ResultBean.error("用户未登录");
 }
 //用户的登出,
@RequestMapping("logout")
    public ResultBean logout(@CookieValue(name=CookieConstant.USER_LOGIN,required = false) String uuid,
                             HttpServletResponse response){
    String redisKey = StringUtil.getRedisKey(CookieConstant.USER_LOGIN, uuid);
    //uuid有可能为空，需要判断
    if(!"".equals(redisKey)&&uuid!=null){
        redisTemplate.delete(redisKey);
    }
    //同时需要将客户端中的cookie删除 ,cookie删除就是做一个覆盖
    Cookie cookie = new Cookie(CookieConstant.USER_LOGIN, "");
    cookie.setMaxAge(0);//删除cookie
    cookie.setPath("/");
    cookie.setHttpOnly(true);//只有后端程序能访问，提高cookie的安全性
    response.addCookie(cookie);
    return ResultBean.success("注销成功");


}




}
