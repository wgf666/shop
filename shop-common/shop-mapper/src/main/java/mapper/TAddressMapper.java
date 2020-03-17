package mapper;

import entity.TAddress;

import java.util.List;

public interface TAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAddress record);

    int insertSelective(TAddress record);

    TAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TAddress record);

    int updateByPrimaryKey(TAddress record);

    void updateById(Integer id);

    List<TAddress> selectAll();

}