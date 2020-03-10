package search.service.service;

import dto.ResultBean;

/**
 * @author:吴小富
 * @Date: 2020/3/10 10:36
 */
public interface ISearchService {

    ResultBean searchByKeyword(String keyword);

    ResultBean addProduct(Long pid);
}
