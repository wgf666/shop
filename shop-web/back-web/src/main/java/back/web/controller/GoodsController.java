package back.web.controller;

import back.web.service.GoodsService;
import back.web.service.ISearchService;
import dto.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private ISearchService searchService;

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
    @RequestMapping("del")
    public entity.ResultBean del(@RequestParam("id") Integer id){
        //假删除
        int resultcount = goodsService.delById(id);
        //删除solr索引库对应数据
        searchService.delById(id);
        if (resultcount>0){
            return new entity.ResultBean(200,"删除成功！");
        }else {
            return new entity.ResultBean(500,"删除失败！");
        }
    }
    @RequestMapping("delBatch")
    public entity.ResultBean delBatch(Integer[] ids){
        int resultcount = goodsService.delBatch(ids);
        //批量删除solr索引库对应数据
        searchService.delByIds(ids);
        if (resultcount>0){
            return new entity.ResultBean(200,"批量删除成功！");
        }else {
            return new entity.ResultBean(500,"批量删除失败！");
        }
    }

}
