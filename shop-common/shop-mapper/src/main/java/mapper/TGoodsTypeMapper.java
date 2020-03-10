package mapper;

import entity.TGoodsType;

import java.util.List;

public interface TGoodsTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TGoodsType record);

    int insertSelective(TGoodsType record);

    TGoodsType selectByPrimaryKey(Integer id);

    List<TGoodsType> selectAll();

    int updateByPrimaryKeySelective(TGoodsType record);

    int updateByPrimaryKey(TGoodsType record);
}