package address.web.feign;

import entity.ResultBean;
import entity.TAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author:吴小富
 * @Date: 2020/3/16 19:14
 */
@FeignClient("address-service")
//@RequestMapping("address")
public interface IAddressService {

    @RequestMapping("address/add")
    public void add(@RequestBody TAddress address);

    @RequestMapping("address/del/{id}")
    public int del(@PathVariable Integer id);

    @RequestMapping("address/defaultById/{id}")
    public void defaultById(@PathVariable Integer id);

}
