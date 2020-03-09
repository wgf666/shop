package mapper;

import entity.TGoodsInfo;

public interface TGoodsInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TGoodsInfo record);

    int insertSelective(TGoodsInfo record);

    TGoodsInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TGoodsInfo record);

    int updateByPrimaryKey(TGoodsInfo record);
}