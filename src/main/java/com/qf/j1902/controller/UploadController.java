package com.qf.j1902.controller;

import com.qf.j1902.pojo.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2019/5/29.
 */
@Controller
public class UploadController {
    @RequestMapping(value = "apply-2",method = RequestMethod.POST)
    public ModelAndView upload(@RequestParam("file")MultipartFile file, HttpServletRequest request
                                    , HttpSession session){
        String realPath = request.getSession().getServletContext().getRealPath("upload");
        String filename = file.getOriginalFilename();
        System.out.println(file);
        String location = realPath +"/"+ filename;
        System.out.println(location);
        File file1 = new File(location);
        try {
            file1.createNewFile();
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserInfo oneByzh =(UserInfo) session.getAttribute("oneByzh");
        oneByzh.setImg("upload/"+filename);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("apply-2");
        return mv;
    }

}
