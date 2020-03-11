package index.service.service.impl;

import dto.ResultBean;
import entity.TGoodsType;
import index.service.service.IGoodsTypeService;
import mapper.TGoodsTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:吴小富
 * @Date: 2020/3/11 12:34
 */
@Service
public class IGoodsTypeServiceImpl implements IGoodsTypeService {

    @Autowired
    private TGoodsTypeMapper goodsTypeMapper;

    @Override
    public ResultBean selectAll() {

        List<TGoodsType> goodsTypeList = goodsTypeMapper.selectAll();
        if(goodsTypeList.size()>=0){
            return ResultBean.success(goodsTypeList);
        }
        return ResultBean.error("无法获取数据，请检查网络");
    }
}
