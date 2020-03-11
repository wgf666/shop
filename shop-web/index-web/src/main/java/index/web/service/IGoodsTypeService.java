package index.web.service;

import dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author:吴小富
 * @Date: 2020/3/11 12:28
 */
@FeignClient("index-service")
public interface IGoodsTypeService {

    @RequestMapping("index")
    public ResultBean showIndex();
}
