package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.RealMapper;
import com.qf.j1902.pojo.RealInfo;
import com.qf.j1902.service.RealInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/5/30.
 */
@Service
public class RealInfoServiceImpl implements RealInfoService{
    @Autowired
    private RealMapper realMapper;

    @Override
    public void insert(RealInfo realInfo) {
        realMapper.insert(realInfo);
    }

    @Override
    public List<RealInfo> getAll() {
        List<RealInfo> all = realMapper.getAll();
        return all;
    }

    @Override
    public RealInfo getOneByzh(String zhanghao) {
        RealInfo oneByzh = realMapper.getOneByzh(zhanghao);
        return oneByzh;
    }

    @Override
    public void updateShyj(RealInfo realInfo) {
        realMapper.updateShyj(realInfo);
    }

    @Override
    public List<RealInfo> getAllByShxx() {
        List<RealInfo> allByShyj = realMapper.getAllByShxx();
        return allByShyj;
    }
}
