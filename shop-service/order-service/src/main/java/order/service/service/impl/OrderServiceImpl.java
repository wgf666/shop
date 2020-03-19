package order.service.service.impl;

import entity.TOrder;
import mapper.TOrderMapper;
import order.service.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:吴小富
 * @Date: 2020/3/19 18:41
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private TOrderMapper orderMapper;

    @Override
    public Integer addOrder(TOrder order) {
        return orderMapper.insertOrder(order);
    }
}
