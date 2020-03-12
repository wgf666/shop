package com.goods.service.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goods.service.service.GoodsService;
import dto.ResultBean;
import entity.TGoodsInfo;
import mapper.TGoodsInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zzp
 * @Date 2020/3/11
 * Do:
 */
@Service
@Component
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private TGoodsInfoMapper goodsInfoMapper;

    @Override
    public ResultBean getPageList(Integer pageIndex,Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<TGoodsInfo> list = goodsInfoMapper.list();
        PageInfo<TGoodsInfo> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo);
        return ResultBean.success(pageInfo);
    }
}
