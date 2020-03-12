package back.web.service;

import com.github.pagehelper.PageInfo;
import dto.ResultBean;
import entity.TGoodsInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author zzp
 * @Date 2020/3/11
 * Do:
 */
@FeignClient(name = "GOODS-SERVICE")
public interface GoodsService {
    @RequestMapping("page")
    public ResultBean getPageList(@RequestParam("pageIndex") Integer pageIndex,@RequestParam("pageSize") Integer pageSize);

    @RequestMapping("del")
    public int delById(@RequestParam("id") Integer id);

    @RequestMapping("delBatch")
    public int delBatch(Integer[] ids);
}
