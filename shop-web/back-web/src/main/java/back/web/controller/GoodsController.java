package back.web.controller;

import back.web.service.GoodsService;
import com.github.pagehelper.PageInfo;
import dto.ResultBean;
import entity.TGoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author zzp
 * @Date 2020/3/11
 * Do:
 */
@Controller
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("welcome")
    public String welcome(){
        return "goods/backWelcome";
    }
//    @RequestMapping("page/{pageIndex}/{pageSize}")
    @RequestMapping("page")
    public String page(Model model, @RequestParam("pageIndex") Integer pageIndex,@RequestParam("pageSize") Integer pageSize){
        ResultBean resultBean = goodsService.getPageList(pageIndex,pageSize);
        model.addAttribute("pageInfo",resultBean.getData());
        return "goods/goodsList";
    }

}
