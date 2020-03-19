package search.service.service;

import dto.ResultBean;
import entity.TGoodsInfo;

/**
 * @author:吴小富
 * @Date: 2020/3/10 10:36
 */
public interface ISearchService {

    ResultBean searchByKeyword(String keyword);

    void delById(Integer id);

    void delByIds(Integer[] ids);

    void updateById(TGoodsInfo goodsInfo);
}
