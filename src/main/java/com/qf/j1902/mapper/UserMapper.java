package com.qf.j1902.mapper;

import com.qf.j1902.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2019/5/27.
 */
public interface UserMapper {
    public List<UserInfo> getAll();

   public void addInfo(UserInfo userInfo);

   public UserInfo findOneByzh(String zhanghao);

   public void addxz(UserInfo userInfo);

   public List<UserInfo> getAll1();

   public boolean delByzh(String zhanghao);

   public UserInfo getOneById(int id);

   public void update(UserInfo userInfo);

   public List<UserInfo> getSome(@Param("info") String info);
}
