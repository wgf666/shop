package com.zzp.payweb.controller;

import com.zzp.payweb.service.PayService;
import entity.TOrder;
import entity.TOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zzp
 * @Date 2020/3/14
 * Do:
 */
@Controller
@RequestMapping("pay")
public class PayController {

    @Autowired
    private PayService payService;

    /**
     *  支付宝支付
     * @param order
     * @param orderDetail
     * @param httpRequest
     * @param httpResponse
     */
    @RequestMapping("dopay")
    public void doPay(TOrder order, TOrderDetail orderDetail,HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        payService.doPay(order,orderDetail,httpRequest,httpResponse);
    }

    /**
     * 支付成功跳转
     * @param model
     * @param order
     * @return String
     */
    @RequestMapping("success")
    public String success(Model model, TOrder order){
        model.addAttribute("order",order);
        System.out.println(order.getId()+","+order.getoShperson());
        return "success";
    }

    /**
     * 支付成功后发送消息给商家
     * @param order
     * @param request
     * @param response
     */
    @RequestMapping("notifyUrl")
    public void notifyUrl(TOrder order,HttpServletRequest request,HttpServletResponse response){
        payService.notifyUrl(order,request,response);
    }
}
