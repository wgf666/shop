package sso.web.service.impl;

import dto.ResultBean;
import entity.TUser;
import mapper.TUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sso.web.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TUserMapper userMapper;


    @Override
    public ResultBean checkLogin(String uname, String password) {
        TUser tUser = userMapper.selectByName(uname);
        if(!tUser.equals("")&tUser!=null){
           if( tUser.getPassword()==password){
               ResultBean resultBean = new ResultBean();
               resultBean.setData(tUser);
               resultBean.setErrno(200);
               resultBean.setMessage("用户名密码正确");
               return  resultBean;
           }else {
               return ResultBean.error("用户名密码不正确");
           }
        }
        return ResultBean.error("没有该用户");
    }
}
