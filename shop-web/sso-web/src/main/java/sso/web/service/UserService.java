package sso.web.service;

import dto.ResultBean;

public interface UserService {
    ResultBean checkLogin(String uname, String password);
}
