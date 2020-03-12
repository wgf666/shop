package constant;

public interface RedisConstant {
    String USER_LOGIN_PRE = "user:login:";
    String REGISTER_PHONE = "register:phone:";
    String REGISTER_EMAIL = "register:email:";
    String USER_CART_PRE = "user:cart:";
    String PRODUCT_PRE = "product:";

    //wgf
    long SESSION_TIMEOUT=60*30;
    long END_TIMEOUT=0*0;
    String REDIS_INDEX = "index";
    String REDIS_INFO = "info";
    String REDIS_TYPE = "type";
}
