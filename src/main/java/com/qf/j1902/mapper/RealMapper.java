package com.qf.j1902.mapper;

import com.qf.j1902.pojo.RealInfo;

import java.util.List;

/**
 * Created by Administrator on 2019/5/30.
 */
public interface RealMapper {
   public void insert(RealInfo realInfo);
   public List<RealInfo> getAll();
   public RealInfo getOneByzh(String zhanghao);
    public List<RealInfo> getAllByShxx();
  public void updateShyj(RealInfo realInfo);
}
