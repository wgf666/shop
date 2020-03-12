package mapper;

import entity.TGoodsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TGoodsInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TGoodsInfo record);

    int insertSelective(TGoodsInfo record);

    TGoodsInfo selectByPrimaryKey(Integer id);

    List<TGoodsInfo> selectAll();

    int updateByPrimaryKeySelective(TGoodsInfo record);

    int updateByPrimaryKey(TGoodsInfo record);

    List<TGoodsInfo> list();

    int delById(Integer id);

    int delBatch(@Param("ids") Integer[] ids);
}