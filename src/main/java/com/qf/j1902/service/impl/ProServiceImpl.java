package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.ProMapper;
import com.qf.j1902.pojo.ProInfo;
import com.qf.j1902.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/6/2 0002.
 */
@Service
public class ProServiceImpl implements ProService {
    @Autowired
   private ProMapper proMapper;
    public void addProInfo(ProInfo proInfo){
        proMapper.addProInfo(proInfo);
    }
}
