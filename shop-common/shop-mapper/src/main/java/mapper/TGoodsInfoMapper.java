package mapper;

import entity.TGoodsInfo;

import java.util.List;

public interface TGoodsInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TGoodsInfo record);

    int insertSelective(TGoodsInfo record);

    TGoodsInfo selectByPrimaryKey(Integer id);

    List<TGoodsInfo> selectAll();

    int updateByPrimaryKeySelective(TGoodsInfo record);

    int updateByPrimaryKey(TGoodsInfo record);


}