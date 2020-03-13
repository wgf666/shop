package order.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author:吴小富
 * @Date: 2020/3/12 21:40
 */
@Controller
public class OrderController {

    @RequestMapping("order/{id}")
    public String addOrder(@PathVariable("id") String id,String sum){
        System.out.println(id+"------"+sum);
        return null;
    }
}
