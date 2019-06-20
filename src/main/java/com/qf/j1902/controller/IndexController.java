package com.qf.j1902.controller;

import com.qf.j1902.mapper.UserMapper;
import com.qf.j1902.pojo.RealInfo;
import com.qf.j1902.pojo.UserInfo;
import com.qf.j1902.service.RealInfoService;
import com.qf.j1902.service.UserService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.Request;
import sun.security.util.Password;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2019/5/27.
 */
@Controller
public class IndexController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "reg", method = RequestMethod.GET)
    public String zhuce() {
        return "reg";
    }

    @Autowired
    private UserService userService;
    @Autowired
    private RealInfoService realInfoService;
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String exit(HttpSession session){
        session.invalidate();
        return "forward:index";
    }
    @RequestMapping(value = "login1", method = RequestMethod.GET)
    public ModelAndView loginMember(@RequestParam("zhanghao") String zhanghao,
                                    @RequestParam("password") String password,
                                    @RequestParam("type") String type,
                                    HttpSession session) {
        System.out.println(zhanghao + password + type);
        List<UserInfo> all = userService.findAll();
        ModelAndView mv = new ModelAndView();
        System.out.println(all);
        if (zhanghao != null && zhanghao != "" && password != null && password != "") {
            for (UserInfo userInfo : all) {
                if (userInfo.getZhanghao().equals(zhanghao) &&
                        userInfo.getPassword().equals(password) &&
                        userInfo.getType().equals("member")) {
                    UserInfo oneByzh = userService.findOneByzh(zhanghao);
                    session.setAttribute("oneByzh", oneByzh);
                    RealInfo realInfo = realInfoService.getOneByzh(zhanghao);
                    System.out.println(realInfo);
                  if (realInfo!=null){
                      String shxx = realInfo.getShxx();
                      if (shxx.equals("通过审核")){
                          String s="已实名认证";
                          mv.addObject("realInfo",realInfo);
                          mv.addObject("s",s);
                          mv.setViewName("member");
                          return mv;
                      }else if(shxx.equals("审核未通过")){
                          String s="审核未通过";
                          mv.addObject("realInfo",realInfo);
                          mv.addObject("s",s);
                          mv.setViewName("member");
                          return mv;
                      } else if(shxx.equals("待审核")){
                          String s="待审核";
                          mv.addObject("realInfo",realInfo);
                          mv.addObject("s",s);
                          mv.setViewName("member");
                          return mv;
                      }
                  } else {
                        String s="未实名认证";
                        RealInfo realInfo1=new RealInfo();
                        realInfo1.setShyj("");
                        mv.addObject("realInfo",realInfo1);
                        mv.addObject("s",s);
                        mv.setViewName("member");
                        return mv;
                    }
                }
                if (userInfo.getZhanghao().equals(zhanghao) &&
                        userInfo.getPassword().equals(password) &&
                        userInfo.getType().equals("user")) {
                    UserInfo oneByzh = userService.findOneByzh(zhanghao);
                    session.setAttribute("oneByzh", oneByzh);
                    mv.setViewName("main");
                    return mv;
                }
            }
        }
        String s = "请输入正确的用户名或密码";
        mv.addObject("s", s);
        mv.setViewName("login");
        return mv;

    }

    @RequestMapping(value = "zhuce", method = RequestMethod.GET)
    public ModelAndView zhuce(@RequestParam("zhanghao") String zhanghao,
                              @RequestParam("password") String password, @RequestParam("email") String email,
                              @RequestParam("type") String type) {
        System.out.println(zhanghao + "--" + password + "--" + email + "--" + type);
        ModelAndView mv = new ModelAndView();
        if (zhanghao != null && zhanghao != "" && password != null && password != ""
                && email != null && email != "") {
            UserInfo member = new UserInfo();
            member.setZhanghao(zhanghao);
            member.setPassword(password);
            member.setEmail(email);
            member.setType(type);
            UserInfo userInfo = userService.findOneByzh(zhanghao);
            System.out.println(userInfo);
            if (userInfo == null) {
                System.out.println(userInfo + "-----");
                userService.add(member);
                mv.setViewName("forward:login");
                return mv;
            }
        }
        String s = "请填写完整信息";
        mv.addObject("s", s);
        mv.setViewName("reg");
        return mv;
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ModelAndView user() {
        List<UserInfo> all = userService.getAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("users", all);
        mv.setViewName("user");
        return mv;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add() {
        return "add";
    }

    @RequestMapping(value = "add1", method = RequestMethod.GET)
    public ModelAndView add1(@RequestParam("zhanghao") String zhanghao,
                             @RequestParam("username") String username,
                             @RequestParam("email") String email) {
        System.out.println(zhanghao + "--" + username + "--" + email);
        ModelAndView mv = new ModelAndView();
        if (zhanghao != null && zhanghao != "" && username != null && username != "" &&
                email != null && email != "") {
            UserInfo oneByzh = userService.findOneByzh(zhanghao);
            System.out.println(oneByzh);
            if (oneByzh == null) {
                UserInfo userInfo = new UserInfo();
                userInfo.setZhanghao(zhanghao);
                userInfo.setUsername(username);
                userInfo.setPassword("666");
                userInfo.setType("member");
                userInfo.setEmail(email);
                System.out.println(userInfo);
                userService.addxz(userInfo);
                List<UserInfo> all = userService.getAll();
                mv.addObject("users", all);
                mv.setViewName("user");
                return mv;
            }
        }
        String s = "请填写完整信息";
        mv.addObject("s", s);
        mv.setViewName("add");
        return mv;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public String del(@RequestParam("zhanghao") String zhanghao) {
        boolean b = userService.delByzh(zhanghao);
        if (b) {
            return "success";
        }
        return "false";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ModelAndView update(HttpServletRequest request, HttpSession session) {
        int id = Integer.parseInt(request.getParameter("id"));
        UserInfo userInfo = userService.getOneById(id);
        session.setAttribute("userinfo", userInfo);
        ModelAndView mv = new ModelAndView();
        mv.addObject("userInfo", userInfo);
        mv.setViewName("edit");
        return mv;
    }

    @RequestMapping(value = "update", method = RequestMethod.GET)
    public ModelAndView update(@RequestParam("zhanghao") String zhanghao,
                               @RequestParam("username") String username,
                               @RequestParam("email") String email,
                               HttpSession session) {
        ModelAndView mv = new ModelAndView();
        UserInfo userinfo = (UserInfo) session.getAttribute("userinfo");
        if (zhanghao != null && zhanghao != "" && email != null && email != "") {
            userinfo.setZhanghao(zhanghao);
            userinfo.setUsername(username);
            userinfo.setEmail(email);
            System.out.println(userinfo);
            userService.update(userinfo);
            List<UserInfo> users = userService.getAll();
            mv.addObject("users", users);
            mv.setViewName("user");
            return mv;
        }
        if (zhanghao == "" || email == "") {
            userinfo.setZhanghao("请填写完整信息");
            userinfo.setUsername("请填写完整信息");
            userinfo.setEmail("请填写完整信息");
            mv.addObject("userInfo", userinfo);
            mv.setViewName("edit");
            return mv;
        }
        return mv;
    }

    @RequestMapping(value = "accttype", method = RequestMethod.GET)
    public String rebzheng() {
        return "accttype";
    }

    @RequestMapping(value = "apply", method = RequestMethod.GET)
    public String shenqing() {
        return "apply";
    }

    @RequestMapping(value = "tpxx", method = RequestMethod.POST)
    @ResponseBody
    public String cunchu(@RequestParam("id") String info,
                         HttpSession session) {
        session.setAttribute("zhxx", info);
        return "success";
    }

    @RequestMapping(value = "chaxun", method = RequestMethod.GET)
    public ModelAndView chaxun(@RequestParam("info") String info) {
        System.out.println(info);
        List<UserInfo> users = userService.findSome(info);
        ModelAndView mv = new ModelAndView();
        mv.addObject("users", users);
        mv.setViewName("user");
        return mv;
    }

    @RequestMapping(value = "panduan", method = RequestMethod.POST)
    @ResponseBody
    public String panduan(HttpSession session) {
        String zhxx = (String) session.getAttribute("zhxx");
        if (zhxx != null && zhxx != "") {
            return "success";
        }
        return "false";
    }

    @RequestMapping(value = "apply", method = RequestMethod.POST)
    public String apply() {
        return "apply";
    }

    @RequestMapping(value = "apply-1", method = RequestMethod.POST)
    public ModelAndView apply1(@RequestParam("username") String username,
                               @RequestParam("idcard") String idcard,
                               @RequestParam("tel") String tel, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        UserInfo oneByzh = (UserInfo) session.getAttribute("oneByzh");
        System.out.println(oneByzh);
        if (username != "" && idcard != "" && tel != "") {
            oneByzh.setUsername(username);
            oneByzh.setIdcard(idcard);
            oneByzh.setTel(tel);
            mv.addObject("userinfo", oneByzh);
            mv.setViewName("apply-1");
            return mv;
        }
        String s = "请填写完整信息";
        mv.addObject("s", s);
        mv.setViewName("apply");
        return mv;
    }

    @RequestMapping(value = "apply-3", method = RequestMethod.POST)
    public ModelAndView apply3(HttpSession session) {
        ModelAndView mv=new ModelAndView();
        String sessionKey = (String) session.getAttribute("SessionKey");
        if (sessionKey !=null && sessionKey !=""){
            mv.setViewName("apply-3");
            return mv;
        }
        String s="请先获取验证码后进行下一步";
        mv.addObject("s",s);
        mv.setViewName("apply-2");
        return mv;
    }

    @RequestMapping(value = "sendyzm", method = RequestMethod.POST)
    @ResponseBody
    public int yxyz(HttpServletRequest request, @RequestParam(defaultValue = "a") String exam,
                    HttpSession session) {
        String regEx1 = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        System.out.println(regEx1+"----------"+exam);
        UserInfo oneByzh =(UserInfo) session.getAttribute("oneByzh");
        oneByzh.setEmail(exam);
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(exam);
        if (m.matches()) {
            try {
                HtmlEmail htmlEmail = new HtmlEmail();
                htmlEmail.setHostName("smtp.qq.com");
                htmlEmail.setCharset("utf-8");
                htmlEmail.addTo(exam);
                htmlEmail.setFrom("1660473326@qq.com", "杨明磊");
                htmlEmail.setAuthentication("1660473326@qq.com", "ozkkjsbdvufdbgaj");
                htmlEmail.setSubject("实名认证验证码");
                int a = (int) ((Math.random() * 9 + 1) * 100000);
                String aa = String.valueOf(a);
                System.out.println(aa);
                session.setAttribute("SessionKey", aa);
                htmlEmail.setMsg("尊贵的会员：您的验证码为" + "<h3>" + aa + "</h3>");
                htmlEmail.send();
            } catch (EmailException e) {
                e.printStackTrace();
            }
            return 200;
        } else {
            return 400;
        }
    }
    @RequestMapping(value = "yzm",method = RequestMethod.GET)
    @ResponseBody
    public String yzm(@RequestParam("yzm")String yzm ,HttpSession session){
        String aa = (String) session.getAttribute("SessionKey");
        System.out.println(aa+"----"+yzm);
        if (yzm.equals(aa)){
            return "success";
        }
        return "false";
    }
    @RequestMapping(value = "member",method = RequestMethod.GET)
    public ModelAndView shenhe(HttpSession session){
        String s="信息正在审核";
        UserInfo oneByzh =(UserInfo) session.getAttribute("oneByzh");
        System.out.println(oneByzh);
        RealInfo realInfo=new RealInfo();
        realInfo.setZhanghao(oneByzh.getZhanghao());
        realInfo.setRealname(oneByzh.getUsername());
        realInfo.setIdimg(oneByzh.getImg());
        realInfo.setIdnum(oneByzh.getIdcard());
        realInfo.setShxx("待审核");
        realInfo.setTelnum(oneByzh.getTel());
        realInfo.setEmail(oneByzh.getEmail());
        realInfo.setZhxx((String) session.getAttribute("zhxx"));
        System.out.println(realInfo);
        realInfoService.insert(realInfo);
        RealInfo oneByzh1 = realInfoService.getOneByzh(oneByzh.getZhanghao());
        ModelAndView mv=new ModelAndView();
        mv.addObject("realInfo",oneByzh1);
        mv.addObject("s",s);
        mv.setViewName("member");
        return mv;
    }
}
