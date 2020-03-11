package com.goods.service.service;

import com.github.pagehelper.PageInfo;
import entity.TGoodsInfo;

/**
 * @Author zzp
 * @Date 2020/3/11
 * Do:
 */
public interface GoodsService {

    PageInfo<TGoodsInfo> getPageList(Integer pageIndex, Integer pageSize);

}
