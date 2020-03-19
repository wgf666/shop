package order.service.service.impl;

import entity.TOrderDetail;
import mapper.TOrderDetailMapper;
import order.service.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:吴小富
 * @Date: 2020/3/19 18:41
 */
@Service
public class OrderDetailServiceImpl implements IOrderDetailService {

    @Autowired
    private TOrderDetailMapper orderDetailMapper;

    @Override
    public void addOrderDetail(TOrderDetail orderDetail) {
        orderDetailMapper.insertSelective(orderDetail);
    }
}
