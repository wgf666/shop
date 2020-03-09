package mapper;

import entity.TGoodsType;

public interface TGoodsTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TGoodsType record);

    int insertSelective(TGoodsType record);

    TGoodsType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TGoodsType record);

    int updateByPrimaryKey(TGoodsType record);
}