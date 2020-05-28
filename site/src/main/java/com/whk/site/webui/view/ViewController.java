package com.whk.site.webui.view;

import com.alibaba.fastjson.JSONObject;
import com.whk.site.client.ResourceClient;
import com.whk.site.domain.dto.AccountRequestDTO;
import com.whk.site.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class ViewController {

    //服务
    @Autowired
    private ResourceClient resourceClient;

    HttpSession session;

    @GetMapping("/attendance")
    public ModelAndView attendance(HttpServletRequest request) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/whk/attendance");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(HttpServletRequest request) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/whk/login");
        return modelAndView;
    }

    @GetMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        session =request.getSession();

        //判断是否登录
        if(session.getAttribute("user")!=null && session.getAttribute("user")!=""){
            String user=(String)session.getAttribute("user");
            modelAndView.setViewName("/whk/index");
            modelAndView.addObject("loginUser",user);

            AccountRequestDTO accountRequestDTO= new AccountRequestDTO();
            accountRequestDTO.setAccountnumber(user);
            JSONObject jsonObject=resourceClient.GetUserInfo(accountRequestDTO);
            if(Result.SUCCESS.CODE.equals(jsonObject.getString("code"))) {
                modelAndView.addObject("userInfo", jsonObject.get("employee"));
            }

        }else{
            modelAndView.setViewName("/whk/login");
        }
        return modelAndView;
    }

    @GetMapping("/fullclendar")
    public ModelAndView fullcalendar(HttpServletRequest request, @RequestParam String loginUser) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loginUser",loginUser);
        modelAndView.setViewName("/whk/fullCalendar");
        return modelAndView;
    }

    @GetMapping("/application_page")
    public ModelAndView application_page(HttpServletRequest request, @RequestParam Integer employeeId,@RequestParam String userName) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name",userName);
        modelAndView.addObject("employeeId",employeeId);
        modelAndView.setViewName("/whk/application_page");
        return modelAndView;
    }

}
