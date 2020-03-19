package search.service.controller;

import dto.ResultBean;
import entity.TGoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import search.service.service.ISearchService;

/**
 * @author:吴小富
 * @Date: 2020/3/10 11:15
 */
@Controller
//@RequestMapping("user")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @RequestMapping("search")
    @ResponseBody
    public ResultBean searchByKeyword(@RequestParam String keyword){
        return searchService.searchByKeyword(keyword);
    }
    @RequestMapping("del")
    @ResponseBody
    public void delById(@RequestParam("id") Integer id){
        searchService.delById(id);
    }

    @RequestMapping("delBatch")
    @ResponseBody
    public void delByIds(Integer[] ids){
        searchService.delByIds(ids);
    }
    //添加与更新都调用update
    @RequestMapping("update")
    @ResponseBody
    public void updateById(TGoodsInfo goodsInfo){
        searchService.updateById(goodsInfo);
    }
}
