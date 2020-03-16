package address.web.feign;

import entity.ResultBean;
import entity.TAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:吴小富
 * @Date: 2020/3/16 19:14
 */
@FeignClient("address-service")
//@RequestMapping("address")
public interface IAddressService {

    @RequestMapping("address/add")
    public void add(@RequestBody TAddress address);

    @RequestMapping("address/del")
    public void del(@RequestParam Integer id);

    @RequestMapping("address/defaultById")
    public void defaultById(@RequestParam Integer id);

}
