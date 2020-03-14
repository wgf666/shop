package com.goods.service.controller;

import com.goods.service.service.GoodsService;
import dto.ResultBean;
import entity.TGoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author zzp
 * @Date 2020/3/11
 * Do:
 */
@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

//    @RequestMapping("page/{pageIndex}/{pageSize}")
    @RequestMapping("page")
    @ResponseBody
    public ResultBean page(@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize){
        return goodsService.getPageList(pageIndex,pageSize);
    }
    @RequestMapping("del")
    @ResponseBody
    public int del(@RequestParam("id") Integer id){
        return goodsService.delById(id);
    }

    @RequestMapping("delBatch")
    @ResponseBody
    public int delBatch(Integer[] ids){
        return goodsService.delBatch(ids);
    }
    @RequestMapping("add")
    @ResponseBody
    public Integer addGood(TGoodsInfo goodsInfo){
        return goodsService.addGood(goodsInfo);
    }
    @RequestMapping("update")
    @ResponseBody
    public void updateGoods(TGoodsInfo goodsInfo){
        goodsService.updateGoods(goodsInfo);
    }
}
