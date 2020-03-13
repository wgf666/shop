package index.service.controller;

import com.google.gson.Gson;
import constant.CookieConstant;
import dto.ResultBean;
import index.service.service.IGoodsInfoService;
import index.service.service.IGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.HttpClientUtils;

import javax.xml.transform.Result;

/**
 * @author:吴小富
 * @Date: 2020/3/11 12:31
 */
@Controller
public class IndexController {

    @Autowired
    private IGoodsTypeService goodsTypeService;

    @Autowired
    private IGoodsInfoService goodsInfoService;

    @RequestMapping("index")
    @ResponseBody
    public ResultBean showIndex(){

        return goodsTypeService.selectAll();
    }

    @RequestMapping("goodsInfo")
    @ResponseBody
    public ResultBean showGoodsInfo(){

        return goodsInfoService.selectAll();
    }




}
