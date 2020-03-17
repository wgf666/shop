package address.service.controller;

import address.service.service.IAddressService;
import entity.TAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("address/del/{id}")
    @ResponseBody
    public int del(@PathVariable Integer id){

        System.out.println(id);
        return addressService.del(id);
    }

    @RequestMapping("address/defaultById/{id}")
    public void defaultById(@PathVariable Integer id){

        System.out.println(id);
//        addressService.defaultById(id);
    }
}
