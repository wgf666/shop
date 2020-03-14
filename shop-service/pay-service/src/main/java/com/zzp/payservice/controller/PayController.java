package com.zzp.payservice.controller;

import com.alipay.api.AlipayApiException;
import com.zzp.payservice.serivce.PayService;
import entity.TOrder;
import entity.TOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author zzp
 * @Date 2020/3/14
 * Do:
 */
@Controller
public class PayController {

    @Autowired
    private PayService payService;

    @RequestMapping("dopay")
    public void doPay(TOrder order, TOrderDetail orderDetail,HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        payService.doPay(order,orderDetail,httpRequest,httpResponse);
    }

    @RequestMapping("notifyUrl")
    public void notifyUrl(TOrder order,HttpServletRequest request,HttpServletResponse response) throws IOException, AlipayApiException {
        payService.notifyUrl(order,request,response);
    }

}
