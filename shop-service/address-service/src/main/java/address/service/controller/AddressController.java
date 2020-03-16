package address.service.controller;

import address.service.service.IAddressService;
import entity.ResultBean;
import entity.TAddress;
import mapper.TAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:吴小富
 * @Date: 2020/3/16 19:24
 */
@Controller
//@RequestMapping("address")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @RequestMapping("address/add")
    @ResponseBody
    public void add(@RequestBody TAddress address){

        System.out.println(address.getAddress());
        addressService.add(address);
    }

    @RequestMapping("del")
    @ResponseBody
    public void del(@RequestParam Integer id){

        System.out.println(id);
        //addressService.add(address);
    }

    @RequestMapping("del")
    @ResponseBody
    public void defaultById(@RequestParam Integer id){

        System.out.println(id);
        //addressService.add(address);
    }
}
