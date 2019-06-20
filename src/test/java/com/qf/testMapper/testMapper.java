package com.qf.testMapper;

import com.qf.j1902.mapper.UserMapper;
import com.qf.j1902.pojo.UserInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Administrator on 2019/5/29.
 */
public class testMapper {
    @Test
    public void testFindSome(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        UserMapper mapper = ac.getBean(UserMapper.class);
        List<UserInfo> some = mapper.getSome("u");
        System.out.println(some);
    }
}
