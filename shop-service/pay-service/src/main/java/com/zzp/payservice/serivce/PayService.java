package com.zzp.payservice.serivce;

import com.alipay.api.AlipayApiException;
import entity.TOrder;
import entity.TOrderDetail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author zzp
 * @Date 2020/3/14
 * Do:
 */
public interface PayService {
    void doPay(TOrder order, TOrderDetail orderDetail, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException;

    void notifyUrl(TOrder order,HttpServletRequest request,HttpServletResponse response) throws AlipayApiException, IOException;
}
