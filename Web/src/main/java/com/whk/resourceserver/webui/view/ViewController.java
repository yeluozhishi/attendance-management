package com.whk.resourceserver.webui.view;

import com.whk.resourceserver.dto.response.sign_dataResponseDTO;
import com.whk.resourceserver.service.ResourceServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class ViewController {

    //资源服务接口
    @Autowired
    ResourceServer resourceServer;

    HttpSession session;

    @GetMapping("/index")
    public ModelAndView index(HttpServletRequest request) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        session =request.getSession();
        //判断是否登录
        if(session.getAttribute("user")!=null && session.getAttribute("user")!=""){
            String user=(String)session.getAttribute("user");
            sign_dataResponseDTO responseDTO=new sign_dataResponseDTO();
            //获取上月签到分析数据
            responseDTO = resourceServer.sign_data();
            modelAndView.addObject("state",responseDTO);
            modelAndView.setViewName("/whk/index");
        }else{
            modelAndView.setViewName("/whk/login");
        }
        return modelAndView;
    }

    @GetMapping("/administrators_login")
    public ModelAndView login(HttpServletRequest request) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/whk/login");
        return modelAndView;
    }

    @GetMapping("/administrators_employeeInfo")
    public ModelAndView employeeInfo(HttpServletRequest request) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        //判断是否登录
        if(session.getAttribute("user")!=null && session.getAttribute("user")!=""){
            String user=(String)session.getAttribute("user");
            modelAndView.setViewName("/whk/employeeInfo");
            modelAndView.addObject("loginUser",user);
        }else{
            modelAndView.setViewName("/whk/login");
        }
        return modelAndView;
    }

    @GetMapping("/administrators_notice")
    public ModelAndView notice(HttpServletRequest request) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        if (session == request.getSession()){
            modelAndView.setViewName("/whk/notice");
            modelAndView.addObject("loginUser",(String)session.getAttribute("user"));
        }else{
            modelAndView.setViewName("/whk/login");
        }
        return modelAndView;
    }

    @GetMapping("/administrators_check")
    public ModelAndView check(HttpServletRequest request) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        if (session == request.getSession()){
            modelAndView.setViewName("/whk/check");
            modelAndView.addObject("loginUser",(String)session.getAttribute("user"));
        }else{
            modelAndView.setViewName("/whk/login");
        }
        return modelAndView;
    }

}
