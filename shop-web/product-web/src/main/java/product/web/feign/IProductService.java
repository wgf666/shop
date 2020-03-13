package product.web.feign;

import entity.TGoodsInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author:吴小富
 * @Date: 2020/3/13 11:07
 */
@FeignClient("product-service")
public interface IProductService {

    @RequestMapping("desc/{id}")
    TGoodsInfo desc(@PathVariable("id") String infoId);

}
