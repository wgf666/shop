package index.service.controller;

import dto.ResultBean;
import index.service.service.IGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:吴小富
 * @Date: 2020/3/11 12:31
 */
@Controller
public class IndexController {

    @Autowired
    private IGoodsTypeService goodsTypeService;

    @RequestMapping("index")
    @ResponseBody
    public ResultBean showIndex(){

        return goodsTypeService.selectAll();
    }
}
