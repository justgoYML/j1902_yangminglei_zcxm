package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.UserMapper;
import com.qf.j1902.pojo.UserInfo;
import com.qf.j1902.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/5/27.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<UserInfo> findAll() {
        List<UserInfo> all = userMapper.getAll();
        return all;
    }

    @Override
    public void add(UserInfo userInfo) {
        userMapper.addInfo(userInfo);
    }

    @Override
    public UserInfo findOneByzh(String zhanghao) {
       UserInfo userInfo= userMapper.findOneByzh(zhanghao);
        return userInfo;
    }

    @Override
    public void addxz(UserInfo userInfo) {
        userMapper.addxz(userInfo);
    }

    @Override
    public List<UserInfo> getAll() {
        List<UserInfo> list = userMapper.getAll1();
        return list;
    }

    @Override
    public boolean delByzh(String zhanghao) {
        boolean bz=userMapper.delByzh(zhanghao);
        return bz;
    }

    @Override
    public void update(UserInfo userInfo) {
        userMapper.update(userInfo);
    }

    @Override
    public List<UserInfo> findSome(String info) {
        List<UserInfo> some = userMapper.getSome(info);
        return some;
    }

    @Override
    public UserInfo getOneById(int id) {
        UserInfo userInfo = userMapper.getOneById(id);
        return userInfo;
    }


}
