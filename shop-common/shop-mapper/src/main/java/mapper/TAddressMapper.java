package mapper;

import entity.TAddress;

public interface TAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAddress record);

    int insertSelective(TAddress record);

    TAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TAddress record);

    int updateByPrimaryKey(TAddress record);
}