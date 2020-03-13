package search.web.feign;

import dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author:吴小富
 * @Date: 2020/3/10 15:32
 */
@FeignClient("search-service")
public interface ISearchService {

    @RequestMapping("search")
    public ResultBean searchByKeyword(@RequestParam String keyword);
}
