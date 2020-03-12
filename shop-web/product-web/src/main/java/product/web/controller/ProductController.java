package product.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author:吴小富
 * @Date: 2020/3/12 21:40
 */
@Controller
public class ProductController {

    @RequestMapping("desc/{id}")
    public String desc(@PathVariable("id") String infoId){
        System.out.println(infoId);
//        return "introduction";
        return null;
    }
}
