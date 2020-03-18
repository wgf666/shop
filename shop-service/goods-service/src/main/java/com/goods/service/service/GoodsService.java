package com.goods.service.service;

import com.github.pagehelper.PageInfo;
import dto.ResultBean;
import entity.TGoodsInfo;

/**
 * @Author zzp
 * @Date 2020/3/11
 * Do:
 */
public interface GoodsService {

    ResultBean getPageList(Integer pageIndex, Integer pageSize);

    int delById(Integer id);

    int delBatch(Integer[] ids);

    Integer addGood(TGoodsInfo goodsInfo);

    void updateGoods(TGoodsInfo goodsInfo);

    TGoodsInfo getGoodById(Integer id);
}
