package com.qf.j1902.controller;

import com.qf.j1902.pojo.ProInfo;
import com.qf.j1902.pojo.RealInfo;
import com.qf.j1902.pojo.UserInfo;
import com.qf.j1902.service.ProService;
import com.qf.j1902.service.RealInfoService;
import com.qf.j1902.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2019/5/30.
 */
@Controller
public class HtController {
    @Autowired
    private UserService userService;
    @Autowired
    private RealInfoService realInfoService;
    @Autowired
    private ProService proService;
    @RequestMapping(value = "auth_cert",method = RequestMethod.GET)
    public ModelAndView ywShenhe(){
        List<RealInfo> all = realInfoService.getAllByShxx();
        System.out.println(all);
        ModelAndView mv=new ModelAndView();
        mv.addObject("reals",all);
        mv.setViewName("auth_cert");
        return mv;
    }
    @RequestMapping(value = "renzheng",method = RequestMethod.GET)
    public ModelAndView renzheng(@RequestParam("zhanghao")String zhanghao, HttpSession session){
        ModelAndView mv=new ModelAndView();
        RealInfo oneByzh = realInfoService.getOneByzh(zhanghao);
        session.setAttribute("renzheng",oneByzh);
        mv.addObject("realInfo",oneByzh);
        mv.setViewName("renzheng");
        return mv;
    }
    @RequestMapping(value = "pass",method = RequestMethod.POST)
    @ResponseBody
    public String pass(@RequestParam("shyj")String shyj,HttpSession session){
        if (shyj!=null && shyj !="" && !shyj.equals("请填写审核意见")){
            RealInfo renzheng = (RealInfo) session.getAttribute("renzheng");
            renzheng.setShyj(shyj);
            renzheng.setShxx("通过审核");
            realInfoService.updateShyj(renzheng);
            System.out.println("修改成功");
            return "success";
        }
        return "false";
    }
    @RequestMapping(value = "sh_back",method = RequestMethod.GET)
    public ModelAndView sh_back(){
        List<RealInfo> allByShyj = realInfoService.getAllByShxx();
        ModelAndView mv=new ModelAndView();
        mv.addObject("reals",allByShyj);
        mv.setViewName("auth_cert");
        return mv;
    }
    @RequestMapping(value = "sh_false",method =RequestMethod.POST)
    @ResponseBody
    public String shFalse(@RequestParam("shyj")String shyj,HttpSession session){
        if (shyj!=null && shyj !="" && !shyj.equals("请填写审核意见")){
            RealInfo renzheng =(RealInfo) session.getAttribute("renzheng");
            renzheng.setShyj(shyj);
            renzheng.setShxx("审核未通过");
            realInfoService.updateShyj(renzheng);
            System.out.println("修改成功");
            return "success";
        }
        return "false";
    }
    @RequestMapping(value = "minecrowdfunding",method = RequestMethod.GET)
    public ModelAndView zhongchou(HttpSession session){
        ModelAndView mv=new ModelAndView();
        UserInfo oneByzh =(UserInfo) session.getAttribute("oneByzh");
        String zhanghao=oneByzh.getZhanghao();
        RealInfo realInfo = realInfoService.getOneByzh(zhanghao);
        mv.addObject("realInfo",realInfo);
        mv.setViewName("minecrowdfunding");
        return mv;
    }
    @RequestMapping(value = "startzc",method = RequestMethod.POST)
    @ResponseBody
    public String startpd(@RequestParam("zhanghao")String zhanghao,HttpSession session){
        RealInfo realInfo = realInfoService.getOneByzh(zhanghao);
        session.setAttribute("proInfo",realInfo);
        String shxx = realInfo.getShxx();
        if (shxx.equals("通过审核")){
            return "success";
        }
        return "false";
    }
    @RequestMapping(value = "start",method = RequestMethod.GET)
    public String startzc(){
        return "start";
    }
    @RequestMapping(value = "start-step-1",method = RequestMethod.GET)
    public String firstStep(){
        return "start-step-1";
    }
//     private String zhanghao;
//private String proname;
//    private String protype;
//    private String proinfo;
//    private String promoney;
//    private String proday;
//    private String leaderimg;
//    private String proimg;
//    private String simintroduce;
//    private String introduce;
//    private String tel;
//    private String servtel;
    @RequestMapping(value = "start-step-2",method = RequestMethod.GET)
    public String step2(@RequestParam(value = "proname")String proname,
                              @RequestParam(value = "protype")String protype,
                              @RequestParam(value = "promoney")String promoney,
                              @RequestParam(value = "proday")String proday,
                              @RequestParam(value = "simintroduce")String simintroduce,
                              @RequestParam(value = "introduce")String introduce,
                              @RequestParam(value = "tel")String tel,
                              @RequestParam(value = "servtel")String servtel,
                        HttpSession session){
        System.out.println(proname+protype+promoney+proday+simintroduce+introduce+tel+servtel);
        RealInfo proInfo1 =(RealInfo) session.getAttribute("proInfo");
        String zhanghao = proInfo1.getZhanghao();
        ProInfo proInfo=new ProInfo();
        proInfo.setZhanghao("zhanghao");
        proInfo.setProname("proname");
        proInfo.setProtype("protype");
        proInfo.setPromoney("promoney");
        proInfo.setProday("proday");
        proInfo.setSimintroduce("simintroduce");
        proInfo.setTel("tel");
        proInfo.setServtel("servtel");
        proService.addProInfo(proInfo);
        System.out.println("添加成功");
        return "start-step-2";
    }
    @RequestMapping(value = "start-step-3" ,method = RequestMethod.GET)
    public String step3(){
        return "start-step-3";
    }
}
