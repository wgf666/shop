package index.web.controller;

import dto.ResultBean;
import index.web.service.IGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author:吴小富
 * @Date: 2020/3/11 12:26
 */
@Controller
public class IndexController {

    @Autowired
    private IGoodsTypeService goodsTypeService;

    @RequestMapping("index")
    public String index(ModelMap map){

        ResultBean resultBean = goodsTypeService.showIndex();
        map.put("goodsType",resultBean.getData());
        return "index";
    }
}
