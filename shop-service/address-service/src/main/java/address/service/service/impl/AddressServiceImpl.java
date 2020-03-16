package address.service.service.impl;

import address.service.service.IAddressService;
import entity.TAddress;
import mapper.TAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:吴小富
 * @Date: 2020/3/16 19:27
 */
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private TAddressMapper addressMapper;

    @Override
    public void add(TAddress address) {
        addressMapper.insertSelective(address);
    }
}
