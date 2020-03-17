package com.zzp.payservice.serivce.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.zzp.payservice.serivce.PayService;
import entity.TOrder;
import entity.TOrderDetail;
import mapper.TOrderDetailMapper;
import mapper.TOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author zzp
 * @Date 2020/3/14
 * Do:
 */
@Service
public class PayServiceImpl implements PayService{

    @Autowired
    private TOrderMapper orderMapper;
    @Autowired
    private TOrderDetailMapper orderDetailMapper;

    /**
     * 使用支付宝支付
     * @param order
     * @param orderDetail
     * @param httpRequest
     * @param httpResponse
     * @throws IOException
     */
    @Override
    public void doPay(TOrder order, TOrderDetail orderDetail, HttpServletRequest httpRequest,HttpServletResponse httpResponse) throws IOException {
        //支付网关  appid   私钥  格式   字符集  支付宝公钥 签名方式
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do",
                "2016101800718610",
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDoapNKJzcTCQ+WhTXhNp9AaqCEEdCQykKJ4pm2wDf1NaiknKnqsspguxYqegUcSNjv/8jg2Frq0Cd15r76aRG5dNKYtxyGWGgHvGETGOXraOjqkasIVnUAbAuhMGRxbNyrsDieTQwcGF49mgN8VdDzsVszt9R1x44vBm1MOolQrDPlMUxpqNoj2mMXM/pQgYYsmAZvBm21b0G8DZWEVwNu0CxcMfxjajdcAVEOSgBXZPIIosKAkEcQ2/5AlrPjfy+7bU7smpdNQAiC9Cm+s4+ugBKbRsUr5obHROQb7vPXvK9vCyrr00OKDuMUA81N/bW5VGgj0NbU7YXDpqzp02xFAgMBAAECggEBAOOM/PpcHLL5axafA9qlpRPnkZLc8kvgi3b+sirYVbExwRSyAja0qCo+4fvijcBcnnGUCHQDwDgoZooryg3X8y+Vbl9w4G+0fN4eFRkihnGOvIR07HR0WYd5QYWmoKDgycka9sQegV28igBcG0+Poc4GdlnTgSNzpwTpkPdSVIDyywKsq3OU6xbKDWhNdPdtzu2NzJxHl5n5hx+p2LnI0W+QAj2ymXBhDQXF7cg7Qp6L/m/4s8VMfFkg0fdf/yrxpGaSlqYk7dd6XBm1shbX/cNvzdgB6/ha5va+k9XgneLAP+oq0qKYRvyi+hiemWnIlzdg5iM0ElMHOBhMh48DaaECgYEA+ByK/5RWqnVKstVKpdqK1geUfJNRTAGAlcxnEAWawEwG3/rxaclmf7UERRBuJVnACLcsLTRrGfDDXPdUrODMR1X5+w3la2K3KhLNjjnIT4xd67vWRtKYl/GmgcCx8cj4DtcoA9uEt98sx+6l1UCrxZiwk9hnmFc/2hlaDakv6Q0CgYEA785Iy/PaVB37QSBl/MqRADRpRYWoFfRzD9mLKJBqZ4dOLjkKYTyqTF6dgMED9/cBwIobzmb3kDMSFBr5MGJVQ4tgc46s/BX5TgGUqBWsZewVHcfv3HwryabXU7t25En5knjYKmvfALjZ8rlKeZ7WPrFtHL5OWfCUeqEsHlKd0hkCgYAxPBDFKN2N09qv5C18/gIg+L4Zfm5ag83NbBNjlYkg8bVHixeO431oiDZBskH5PXcjA55LliROsH1EHHx8FdvTqBcEH7zP83Xfbn3F5GeAiZ01oz+hLDdFp5lgFCn7jeLTpxR0t4H9rtbHEe9sgvnTKzqXDBF6pI2yLOe24ET2nQKBgF9/pAfsxa6n/e4yq8rfFJ2jQaUZFBYiMkxGkx+FW4p9HMpVluegVCVzrH+g3ia6rGzz0x0Abmhznih5kXEOR+xNLgz27ba6PX0uLfg+IVi5irNAcyo4HOwCUFAdILzLJOOK0nMaYEWWV91MovcKJUH0QIKo2uUAZPDtDHfK6OTJAoGAfAgmUk+uLAAsomc8iyqRi6QaX6hJIZY88cb2ArVgYVvydwmjbGLFA/KJOccf7DPffgtjo35GSn8EcyS5N6fvK4P7C3/rAWuBlV/6c70jdlbeUkP0kzV6a8Lzh8Dk/QMg/kX4saG77cnwSaeb4HctwlYUe6xv5N9JYND2hCKbypk=",
                "JSON",
                "UTF-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA/xdyc9VH3QKpaAQhxtlTds3JZwvZvIH+C47pXHgSLkndoO4EH1JPEmwjWnQn+Ko/z96/8sb80x0+P5DBfasBTrW3D9fuYROXzhcdDOGg8zeKFKnQdqUpgoF+4YCwViZlSuNNRjAdTwDChyPl1zNt9fuEUm40SXn8OF7hp7HJm+dufzHY/luk9Fj353h/bllR3WSEAG14oCnxB1K/29v51Ny8OCnnWlWqTJtcNaThm3mTlAAtXX5ppfoOVhr+C4dnHx6aTWRHs/qa4/xiK38j/EbPfChBPa7abYLwoesGq+rxvYjFFY+8OOIQcEISa2W06KrERkxcSdC/oR2qNTg1AQIDAQAB",
                "RSA2"); //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        //使用内网穿透，注意内网穿透地址变化
        alipayRequest.setReturnUrl("http://hrufwh.natappfree.cc/success");
        alipayRequest.setNotifyUrl("http://hrufwh.natappfree.cc/notifyUrl?orderid="+order.getId());//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+order.getId()+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+order.getoPaycount()+"," +
                "    \"subject\":\""+orderDetail.getGoodsname()+"\"," +
                "    \"body\":\" "+orderDetail.getGoodsname()+"\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\""+orderDetail.getGoodsid()+"\"" +
                "    }"+
                "  }");//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=UTF-8");
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();

        //添加信息到数据库
        orderMapper.insertSelective(order);
        orderDetailMapper.insertSelective(orderDetail);
    }

    /**
     * 返给商家支付信息
     * @param order
     * @param request
     * @param response
     * @throws AlipayApiException
     * @throws IOException
     */
    @Override
    public void notifyUrl(TOrder order,HttpServletRequest request,HttpServletResponse response) throws AlipayApiException, IOException {
        Map<String, String[]> map = request.getParameterMap();

        Map<String, String> paramsMap = new HashMap<>(); //将异步通知中收到的所有参数都存放到 map 中
        Set<Map.Entry<String, String[]>> entries = map.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            String[] values = entry.getValue();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < values.length-1; i++) {
                sb.append(values[i]+",");
            }
            sb.append(values[values.length-1]);
            paramsMap.put(entry.getKey(),sb.toString());
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap,
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA/xdyc9VH3QKpaAQhxtlTds3JZwvZvIH+C47pXHgSLkndoO4EH1JPEmwjWnQn+Ko/z96/8sb80x0+P5DBfasBTrW3D9fuYROXzhcdDOGg8zeKFKnQdqUpgoF+4YCwViZlSuNNRjAdTwDChyPl1zNt9fuEUm40SXn8OF7hp7HJm+dufzHY/luk9Fj353h/bllR3WSEAG14oCnxB1K/29v51Ny8OCnnWlWqTJtcNaThm3mTlAAtXX5ppfoOVhr+C4dnHx6aTWRHs/qa4/xiK38j/EbPfChBPa7abYLwoesGq+rxvYjFFY+8OOIQcEISa2W06KrERkxcSdC/oR2qNTg1AQIDAQAB",
                "UTF-8",
                "RSA2"); //调用SDK验证签名
        if(signVerified){
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            if (paramsMap.get("out_trade_no").equals(order.getId())&&paramsMap.get("total_amount").equals(order.getoPaycount())){
                System.out.println("验签成功！");
                response.getWriter().write("{\n" +
                        "    \"alipay_trade_page_pay_response\": {\n" +
                        "        \"code\": \"10000\",\n" +
                        "        \"msg\": \"Success\",\n" +
                        "        \"trade_no\": \"2013112011001004330000121536\",\n" +
                        "        \"out_trade_no\": \"6823789339978248\",\n" +
                        "        \"seller_id\": \"2088111111116894\",\n" +
                        "        \"total_amount\": 128,\n" +
                        "        \"merchant_order_no\": \"20161008001\"\n" +
                        "    },\n" +
                        "    \"sign\": \"ERITJKEIJKJHKKKKKKKHJEREEEEEEEEEEE\"\n" +
                        "}");//阿里提供的JSON数据
            }
        }else{
            // TODO 验签失败则记录异常日志，并在response中返回failure.44
            response.getWriter().write("{\n" +
                    "    \"alipay_trade_page_pay_response\": {\n" +
                    "        \"code\": \"20000\",\n" +
                    "        \"msg\": \"Service Currently Unavailable\",\n" +
                    "        \"sub_code\": \"isp.unknow-error\",\n" +
                    "        \"sub_msg\": \"系统繁忙\"\n" +
                    "    },\n" +
                    "    \"sign\": \"ERITJKEIJKJHKKKKKKKHJEREEEEEEEEEEE\"\n" +
                    "}");
        }
    }
}
