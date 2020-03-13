package product.service.controller;

import entity.TGoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import product.service.service.IProductService;

import java.util.List;

/**
 * @author:吴小富
 * @Date: 2020/3/13 11:14
 */
@Controller
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("desc/{id}")
    @ResponseBody
    public TGoodsInfo desc(@PathVariable("id") String infoId){
        return productService.desc(infoId);
    }
}
