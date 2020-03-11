package back.web.service;

import com.github.pagehelper.PageInfo;
import entity.TGoodsInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author zzp
 * @Date 2020/3/11
 * Do:
 */
@FeignClient(name = "GOODS-SERVICE")
public interface GoodsService {
    @RequestMapping("page/{pageIndex}/{pageSize}")
    PageInfo<TGoodsInfo> getPageList(Integer pageIndex, Integer pageSize);

}
