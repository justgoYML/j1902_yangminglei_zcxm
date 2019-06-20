package com.qf.j1902.service;

import com.qf.j1902.pojo.UserInfo;
import sun.security.util.Password;

import java.util.List;

/**
 * Created by Administrator on 2019/5/27.
 */
public interface UserService {
    public List<UserInfo> findAll();
    public void  add(UserInfo userInfo);
    public UserInfo findOneByzh(String zhanghao);
    public void addxz(UserInfo userInfo);
    public List<UserInfo> getAll();
    public boolean delByzh(String zhanghao);
    public void update(UserInfo userInfo);
    public List<UserInfo> findSome(String info);
   public UserInfo getOneById(int id);
}
