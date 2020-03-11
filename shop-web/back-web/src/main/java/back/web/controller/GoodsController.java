package back.web.controller;

import back.web.service.GoodsService;
import com.github.pagehelper.PageInfo;
import entity.TGoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping("page/{pageIndex}/{pageSize}")
    public String page(Model model, @PathVariable Integer pageIndex, @PathVariable Integer pageSize){
        PageInfo<TGoodsInfo> pageInfo = goodsService.getPageList(pageIndex,pageSize);
        model.addAttribute("pageInfo",pageInfo);
        return "goods/goodsList";
    }

}
