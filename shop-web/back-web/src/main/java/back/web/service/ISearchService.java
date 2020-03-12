package back.web.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author zzp
 * @Date 2020/3/13
 * Do:
 */
@FeignClient(name = "SEARCH-SERVICE")
public interface ISearchService {

    @RequestMapping("del")
    public void delById(@RequestParam("id") Integer id);

    @RequestMapping("delBatch")
    public void delByIds(Integer[] ids);
}
