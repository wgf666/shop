package search.web.controller;

import dto.ResultBean;
import entity.TGoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import search.web.service.ISearchService;

/**
 * @author:吴小富
 * @Date: 2020/3/10 15:23
 */
@Controller
@RequestMapping("wgf")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @RequestMapping("search")
    public String search(@RequestParam("index_none_header_sysc") String keyword, ModelMap map){
        ResultBean resultBean = searchService.searchByKeyword(keyword);

        map.put("goodsInfo",resultBean.getData());
        map.put("va",keyword);
        return "search";
    }
}
