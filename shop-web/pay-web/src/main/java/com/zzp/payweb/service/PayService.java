package com.zzp.payweb.service;

import entity.TOrder;
import entity.TOrderDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zzp
 * @Date 2020/3/14
 * Do:
 */
@FeignClient(name = "PAY-SERVICE")
public interface PayService {
    @RequestMapping("dopay")
    public void doPay(TOrder order, TOrderDetail orderDetail, HttpServletRequest httpRequest, HttpServletResponse httpResponse);

    @RequestMapping("notifyUrl")
    public void notifyUrl(TOrder order,HttpServletRequest request,HttpServletResponse response);
}
