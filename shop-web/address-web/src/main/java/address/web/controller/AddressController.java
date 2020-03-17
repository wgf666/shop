package address.web.controller;

import address.web.feign.IAddressService;
import entity.TAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:吴小富
 * @Date: 2020/3/16 10:50
 */
@Controller
@RequestMapping("address")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @RequestMapping("add")
    @ResponseBody
    public void add(TAddress address){
        address.setUserid(5);
        System.out.println(address.getAddress());
        //return "ad";
//        addressService.add(address);
    }

    @RequestMapping("del/{id}")
    @ResponseBody
    public int del(@PathVariable Integer id){

        System.out.println(id);
        //return addressService.del(id);
        return 0;
    }

    @RequestMapping("default/{id}")
    public void defaultById(@PathVariable Integer id){

        System.out.println(id);
        //addressService.defaultById(id);
    }
}
