package product.web.controller;

import entity.TGoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import product.web.feign.IProductService;

/**
 * @author:吴小富
 * @Date: 2020/3/12 21:40
 */
@Controller
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("desc/{id}")
    public String desc(@PathVariable("id") String infoId, ModelMap map){

        TGoodsInfo goodsInfo = productService.desc(infoId);

        map.put("goodsInfo",goodsInfo);
        System.out.println(infoId);
        System.out.println(goodsInfo);
        return "introduction";
//        return null;
    }
}
