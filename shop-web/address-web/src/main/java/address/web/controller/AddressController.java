package address.web.controller;

import address.web.feign.IAddressService;
import entity.TAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public void add(TAddress address,String sheng,String shi,String qu){
        address.setAddress(sheng+" "+shi+" "+qu+" "+address.getAddress());

        address.setUserid(5);
        System.out.println(address.getAddress());
        addressService.add(address);
    }

    @RequestMapping("del")
    public void del(Integer id){

        System.out.println(id);
        //addressService.add(address);
    }

    @RequestMapping("default")
    public void defaultById(Integer id){

        System.out.println(id);
        //addressService.add(address);
    }
}
