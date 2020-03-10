package search.service.controller;

import dto.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import search.service.service.ISearchService;

/**
 * @author:吴小富
 * @Date: 2020/3/10 11:15
 */
@Controller
@RequestMapping("user")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @RequestMapping("search")
    public ResultBean searchByKeyword(@RequestParam String keyword){
        return searchService.searchByKeyword(keyword);
    }
}
