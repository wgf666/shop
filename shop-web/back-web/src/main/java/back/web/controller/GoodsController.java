package back.web.controller;

import back.web.service.GoodsService;
import back.web.service.ISearchService;
import dto.ResultBean;
import entity.TGoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    /**
     *  后台欢迎
     * @return
     */
    @RequestMapping("welcome")
    public String welcome(){
        return "goods/backWelcome";
    }

    /**
     *  分页查询展示
     * @param model
     * @param pageIndex
     * @param pageSize
     * @return String
     */
//    @RequestMapping("page/{pageIndex}/{pageSize}")
    @RequestMapping("page")
    public String page(Model model, @RequestParam("pageIndex") Integer pageIndex,@RequestParam("pageSize") Integer pageSize){
        ResultBean resultBean = goodsService.getPageList(pageIndex,pageSize);
        model.addAttribute("pageInfo",resultBean.getData());
        return "goods/goodsList";
    }

    /**
     * 删除单个商品
     * 数据库 solr
     * @param id
     * @return ResultBean
     */
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

    /**
     * 删除多个商品
     * 数据库 solr
     * @param ids
     * @return ResultBean
     */
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

    /**
     * 添加商品信息
     * @param goodsInfo
     * @return String
     */
    @RequestMapping("add")
    public String add(TGoodsInfo goodsInfo){
        Integer goodId = goodsService.addGood(goodsInfo);
        //搜索索引库
        searchService.updateById(goodId);
        //生成详情页面
//        itemService.createHtmlById(goodId);
        return "redirect:/goods/page?pageIndex=1&pageSize=5";
    }

    /**
     * 修改商品信息
     * @param goodsInfo
     * @return String
     */
    @RequestMapping("update")
    public String update(TGoodsInfo goodsInfo){
        goodsService.updateGoods(goodsInfo);
        //更新solr索引库
        searchService.updateById(goodsInfo.getId());
        return "redirect:/goods/page?pageIndex=1&pageSize=5";
    }

}
