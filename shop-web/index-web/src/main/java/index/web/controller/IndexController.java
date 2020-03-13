package index.web.controller;

import com.google.gson.Gson;
import constant.CookieConstant;
import dto.ResultBean;
import index.web.feign.IGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.HttpClientUtils;

/**
 * @author:吴小富
 * @Date: 2020/3/11 12:26
 */
@Controller
public class IndexController {

    @Autowired
    private IGoodsTypeService goodsTypeService;


    @RequestMapping({"","index"})
    public String index(ModelMap map){

        ResultBean goodsType = goodsTypeService.showIndex();
        ResultBean goodsInfo = goodsTypeService.showGoodsInfo();

        map.put("goodsType",goodsType.getData());
        map.put("goodsInfo",goodsInfo.getData());


        return "index";
    }

    @RequestMapping("checkIsLogin")
    @ResponseBody
    public ResultBean checkIsLogin(@CookieValue(name= CookieConstant.USER_LOGIN,required = false)String uuid){

        String url = "http://localhost:9001/checkIsLogin";
        String cookie = new StringBuilder().append(CookieConstant.USER_LOGIN).append("=").append(uuid).toString();
        String result = HttpClientUtils.doGet(url, cookie);
        Gson gson = new Gson();
        ResultBean resultBean = gson.fromJson(result, ResultBean.class);

        return   resultBean;
    }
}
