package com.whk.site.webui.rest;

import com.alibaba.fastjson.JSONObject;
import com.whk.site.client.ResourceClient;
import com.whk.site.domain.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/server")
public class RestController {

    HttpSession session;

    //服务
    @Autowired
    private ResourceClient resourceClient;

    @PostMapping("/login")
    public JSONObject login(@RequestParam(required = true) String userName,@RequestParam(required = true) String password) throws Exception {
        //在Spirng或Springboot中获取HttpServletRequest
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        session = request.getSession();

        LoginUserRequestDTO requestDTO = new LoginUserRequestDTO();
        requestDTO.setAccountnumber(userName);
        requestDTO.setPassword(password);
        JSONObject jsonObject=resourceClient.login(requestDTO);

        String code = jsonObject.getString("code");
        if(code.equals("10000")){
            session.setAttribute("user",userName);
            session.setAttribute("psw",password);
        }
        return jsonObject;
    }

    @PostMapping("/sign")
    public JSONObject sign() throws Exception {
        JSONObject jsonObject;
        if(session.getAttribute("user")!=null && session.getAttribute("user")!=""){
            SignDiaryRequestDTO signDiaryRequestDTO=new SignDiaryRequestDTO();
            signDiaryRequestDTO.setAccountnumber((String) session.getAttribute("user"));
            jsonObject=resourceClient.sign(signDiaryRequestDTO);
        }else {
            jsonObject=new JSONObject();
            jsonObject.put("code","10001");
            jsonObject.put("error","打卡失败！重新登录");
        }
        return jsonObject;
    }

    @PostMapping("/memorandum_save")
    public JSONObject memorandum_save(String title, String content, Date belongDate) throws Exception {
        JSONObject jsonObject;
        if(session.getAttribute("user")!=null && session.getAttribute("user")!=""){
            MemorandumRequestDTO requestDTO=new MemorandumRequestDTO();
            requestDTO.setAccountnumber((String) session.getAttribute("user"));
            requestDTO.setTitle(title);
            requestDTO.setContent(content);
            requestDTO.setBelongDate(belongDate);
            jsonObject=resourceClient.memorandum_save(requestDTO);
        }else {
            jsonObject=new JSONObject();
            jsonObject.put("code","10001");
            jsonObject.put("error","保存失败！重新登录");
        }
        return jsonObject;
    }

    @PostMapping("/memorandum_search")
    public JSONObject memorandum_search() throws Exception {
        JSONObject jsonObject;
        if(session.getAttribute("user")!=null && session.getAttribute("user")!=""){
            MemorandumRequestDTO requestDTO=new MemorandumRequestDTO();
            requestDTO.setAccountnumber((String) session.getAttribute("user"));
            //最大获取1000条数据
            requestDTO.setPageNo(1);
            requestDTO.setPageSize(1000);
            jsonObject=resourceClient.memorandum_search(requestDTO);
        }else {
            jsonObject=new JSONObject();
            jsonObject.put("code","10001");
            jsonObject.put("error","获取备忘录失败！重新登录");
        }
        return jsonObject;
    }

    @PostMapping("/SearchSignItem")
    public JSONObject SearchSignItem( Date start, Date end) throws Exception {
        JSONObject jsonObject;
        if(session.getAttribute("user")!=null && session.getAttribute("user")!=""){
            SignRequestDTO requestDTO=new SignRequestDTO();
            requestDTO.setAccountnumber((String) session.getAttribute("user"));
            requestDTO.setStartTime(start);
            requestDTO.setEndTime(end);
            requestDTO.setPageNo(1);
            requestDTO.setPageSize(35);
            jsonObject=resourceClient.SearchSignItem(requestDTO);
        }else {
            jsonObject=new JSONObject();
            jsonObject.put("code","10001");
            jsonObject.put("error","获取签到记录失败！重新登录");
        }
        return jsonObject;
    }

    @PostMapping("/GetOffworkApplicationItem")
    public JSONObject GetOffworkApplicationItem(Integer employeeId,Integer pageNo) throws Exception {
        JSONObject jsonObject;
        SearchOffWorkApplicationRequestDTO requestDTO = new SearchOffWorkApplicationRequestDTO();
        if(session.getAttribute("user")!=null && session.getAttribute("user")!=""){
            requestDTO.setEmployeeId(employeeId);
            requestDTO.setPageNo(pageNo);
            requestDTO.setPageSize(18);
            jsonObject = resourceClient.GetOffworkApplicationItem(requestDTO);

        }else {
            jsonObject=new JSONObject();
            jsonObject.put("code","10001");
            jsonObject.put("error","获取申请记录失败！重新登录");
        }
        return jsonObject;
    }

    @PostMapping("/OffworkApplicationItem")
    public JSONObject OffworkApplicationItem(Integer employeeId,String applicationType,String applicationReason,Date startTime,Date endTime) throws Exception {
        JSONObject jsonObject;
        OffWorkApplicationRequestDTO requestDTO = new OffWorkApplicationRequestDTO();
        if(session.getAttribute("user")!=null && session.getAttribute("user")!=""){
            requestDTO.setEmployeeId(employeeId);
            requestDTO.setApplicationType(applicationType);
            requestDTO.setApplicationReason(applicationReason);
            requestDTO.setStartTime(startTime);
            requestDTO.setEndTime(endTime);
            jsonObject = resourceClient.OffworkApplicationItem(requestDTO);

        }else {
            jsonObject=new JSONObject();
            jsonObject.put("code","10001");
            jsonObject.put("error","获取申请记录失败！重新登录");
        }
        return jsonObject;
    }

    @PostMapping("/notice_get")
    public JSONObject mission_get(int pageNo) throws Exception {
        JSONObject jsonObject;
        Mission_getRequestDTO requestDTO = new Mission_getRequestDTO();
        if(session.getAttribute("user")!=null && session.getAttribute("user")!=""){
            requestDTO.setAccountnumber_ee((String) session.getAttribute("user"));
            requestDTO.setPageNo(pageNo);
            requestDTO.setPageSize(5);
            jsonObject = resourceClient.notice_get(requestDTO);

        }else {
            jsonObject=new JSONObject();
            jsonObject.put("code","10001");
            jsonObject.put("error","获取申请记录失败！重新登录");
        }
        return jsonObject;
    }

    @PostMapping("/logout")
    public JSONObject logout() throws Exception {
        JSONObject jsonObject=new JSONObject();
        Mission_getRequestDTO requestDTO = new Mission_getRequestDTO();
        if(session.getAttribute("user")!=null && session.getAttribute("user")!="") {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            request.getSession().removeAttribute("user");
            request.getSession().removeAttribute("psw");
        }
        return jsonObject;
    }

    @PostMapping("/signState")
    public JSONObject signState() throws Exception {
        JSONObject jsonObject=new JSONObject();
        SignDiaryRequestDTO requestDTO = new SignDiaryRequestDTO();
        if(session.getAttribute("user")!=null && session.getAttribute("user")!=""){
            requestDTO.setAccountnumber((String) session.getAttribute("user"));
            jsonObject = resourceClient.signState(requestDTO);

        }else {
            jsonObject=new JSONObject();
            jsonObject.put("code","10001");
            jsonObject.put("error","获取申请记录失败！重新登录");
        }
        return jsonObject;
    }

    @PostMapping("/update_psw")
    public JSONObject update_psw(String loginUser,String psw_old,String psw_new) throws Exception {
        JSONObject jsonObject=new JSONObject();
        AccountRequestDTO requestDTO = new AccountRequestDTO();
        requestDTO.setAccountnumber(loginUser);
        requestDTO.setPassword(psw_old);
        requestDTO.setPsw_new(psw_new);
        jsonObject = resourceClient.update_psw(requestDTO);

        return jsonObject;
    }
}
