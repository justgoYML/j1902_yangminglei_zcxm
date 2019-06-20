package com.qf.j1902.pojo;

import lombok.Data;

/**
 * Created by Administrator on 2019/5/30.
 */
@Data
public class RealInfo {
    private int id;
    private String zhanghao;
    private String realname;
    private String idimg;
    private String idnum;
    private String email;
    private String telnum;
    private String zhxx;
    private String shxx;//审核信息
    private String shyj;//审核意见
}
