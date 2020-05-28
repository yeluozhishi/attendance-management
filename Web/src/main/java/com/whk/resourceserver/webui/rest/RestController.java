package com.whk.resourceserver.webui.rest;

import com.whk.resourceserver.dto.common.PageResponseDTO;
import com.whk.resourceserver.dto.common.ResultDTO;
import com.whk.resourceserver.dto.request.*;
import com.whk.resourceserver.dto.response.AccountResponseDTO;
import com.whk.resourceserver.dto.response.sign_dataResponseDTO;
import com.whk.resourceserver.entity.employeeEntity;
import com.whk.resourceserver.entity.offworkapplicationEntity;
import com.whk.resourceserver.entity.signEntity;
import com.whk.resourceserver.service.ResourceServer;
import com.whk.resourceserver.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/administrators")
@Api("管理接口")
public class RestController {

    HttpSession session;

    //资源服务接口
    @Autowired
    private ResourceServer resourceServer;

    @RequestMapping(value = "/login",method= RequestMethod.POST)
    @ApiOperation(value="登录")
    public ResultDTO login(@RequestParam(required = true) String userName, @RequestParam(required = true) String password) throws Exception {
        //在Spirng或Springboot中获取HttpServletRequest
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        session = request.getSession();

        ResultDTO resultDTO=new ResultDTO();
        ManagerRequestDTO requestDTO=new ManagerRequestDTO();
        requestDTO.setAccountnumber(userName);
        requestDTO.setPassword(password);
        resultDTO=this.resourceServer.administrators_login(requestDTO);

        if(resultDTO.getCode().equals("10000")){
            session.setAttribute("user",userName);
            session.setAttribute("psw",password);
        }

        return resultDTO;
    }

    @RequestMapping(value = "/SearchEmployee",method= RequestMethod.POST)
    @ApiOperation(value="获取职员列表")
    public PageResponseDTO<employeeEntity> SearchEmployee(String condition, int pageNo, int pageSize) throws Exception {
        //可对id精确搜索
        // 姓名、职位模糊搜索
        PageResponseDTO<employeeEntity> resultDTO=new PageResponseDTO<employeeEntity>();
        SearchEmployeeRequestDTO requestDTO = new SearchEmployeeRequestDTO();
        if (condition==""){
            condition=null;
        }
        try{
            int id = Integer.parseInt(condition);
            requestDTO.setEmployeeId(id);
        }catch (Exception e){
            requestDTO.setCondition(condition);
        }
        requestDTO.setPageNo(pageNo);
        requestDTO.setPageSize(pageSize);
        resultDTO=this.resourceServer.SearchEmployee(requestDTO);

        return resultDTO;
    }

    @RequestMapping(value = "/DeleteEmployee",method= RequestMethod.POST)
    @ApiOperation(value="删除单个职员")
    public ResultDTO DeleteEmployee(int employeeId) throws Exception {

        ResultDTO resultDTO=new ResultDTO();
        DeleteEmployeeRequestDTO requestDTO = new DeleteEmployeeRequestDTO();
        requestDTO.setEmployeeId(employeeId);
        resultDTO=this.resourceServer.DeleteEmployeee(requestDTO);

        return resultDTO;
    }

    @RequestMapping(value = "/UpdateEmployee",method= RequestMethod.POST)
    @ApiOperation(value="更新职员信息")
    public ResultDTO UpdateEmployee(int employeeId, String name, String  sex, Integer age, String position, BigDecimal salary, Date entryTime,Date quitTime) throws Exception {

        char s=sex.charAt(0);
        ResultDTO resultDTO=new ResultDTO();
        UpdateEmployeeRequestDTO requestDTO = new UpdateEmployeeRequestDTO();
        requestDTO.setEmployeeId(employeeId);
        requestDTO.setName(name);
        requestDTO.setSex(s);
        requestDTO.setAge(age);
        requestDTO.setPosition(position);
        requestDTO.setSalary(salary);
        requestDTO.setEntryTime(entryTime);
        requestDTO.setQuitTime(quitTime);
        resultDTO=this.resourceServer.UpdateEmployee(requestDTO);

        return resultDTO;
    }

    @RequestMapping(value = "/GetAccount",method= RequestMethod.POST)
    @ApiOperation(value="获取职员账户")
    public AccountResponseDTO GetAccount(int employeeId) throws Exception {

        AccountResponseDTO resultDTO=new AccountResponseDTO();
        AccountRequestDTO requestDTO = new AccountRequestDTO();
        requestDTO.setEmployeeId(employeeId);
        resultDTO=this.resourceServer.SearchAccountInfo(requestDTO);

        return resultDTO;
    }

    @RequestMapping(value = "/AddAccount",method= RequestMethod.POST)
    @ApiOperation(value="添加职员账户")
    public ResultDTO AddAccount(int employeeId,String account ,String password) throws Exception {

        ResultDTO resultDTO=new ResultDTO();
        AccountRequestDTO requestDTO = new AccountRequestDTO();
        requestDTO.setEmployeeId(employeeId);
        requestDTO.setAccountnumber(account);
        requestDTO.setPassword(password);
        resultDTO=this.resourceServer.AddAccount(requestDTO);

        return resultDTO;
    }

    @RequestMapping(value = "/DeleteAccount",method= RequestMethod.POST)
    @ApiOperation(value="删除职员账户")
    public ResultDTO DeleteAccount(int employeeId) throws Exception {

        ResultDTO resultDTO=new ResultDTO();
        AccountRequestDTO requestDTO = new AccountRequestDTO();
        requestDTO.setEmployeeId(employeeId);
        resultDTO=this.resourceServer.DeleteAccount(requestDTO);

        return resultDTO;
    }

    @RequestMapping(value = "/AddEmployee",method= RequestMethod.POST)
    @ApiOperation(value="添加职员信息")
    public ResultDTO AddEmployee(String name, String  sex, Integer age, String position, BigDecimal salary) throws Exception {
        char s=sex.charAt(0);
        ResultDTO resultDTO=new ResultDTO();
        UpdateEmployeeRequestDTO requestDTO = new UpdateEmployeeRequestDTO();
        requestDTO.setName(name);
        requestDTO.setSex(s);
        requestDTO.setAge(age);
        requestDTO.setPosition(position);
        requestDTO.setSalary(salary);

        resultDTO=this.resourceServer.AddEmployee(requestDTO);

        return resultDTO;
    }

    @RequestMapping(value = "/notice_get",method= RequestMethod.POST)
    @ApiOperation(value="获取公告信息")
    public PageResponseDTO notice_get(String condition,int pageNo,int pageSize) throws Exception {
        //对id精确搜索
        //姓名、账户、内容模糊搜索
        PageResponseDTO resultDTO=new PageResponseDTO();
        notice_getRequestDTO requestDTO = new notice_getRequestDTO();
        if (condition==""){
            condition=null;
        }
        try{
            int id = Integer.parseInt(condition);
            requestDTO.setId(id);
        }catch (Exception e){
            if (condition!=null){
                requestDTO.setCondition(condition);
            }
        }
        requestDTO.setPageNo(pageNo);
        requestDTO.setPageSize(pageSize);
        resultDTO=this.resourceServer.notice_get(requestDTO);

        return resultDTO;
    }

    @RequestMapping(value = "/notice_delete",method= RequestMethod.POST)
    @ApiOperation(value="删除公告信息")
    public ResultDTO notice_delete(int id) throws Exception {
        ResultDTO resultDTO=new ResultDTO();
        notice_getRequestDTO requestDTO = new notice_getRequestDTO();
        requestDTO.setId(id);
        resultDTO=this.resourceServer.notice_delete(requestDTO);

        return resultDTO;
    }

    @RequestMapping(value = "/notice_update",method= RequestMethod.POST)
    @ApiOperation(value="更新公告信息")
    public ResultDTO notice_update(int id,String name,String content) throws Exception {
        ResultDTO resultDTO=new ResultDTO();
        notice_getRequestDTO requestDTO = new notice_getRequestDTO();
        requestDTO.setId(id);
        requestDTO.setName(name);
        requestDTO.setContent(content);
        requestDTO.setAccount("manager");
        resultDTO=this.resourceServer.notice_update(requestDTO);

        return resultDTO;
    }

    @RequestMapping(value = "/notice_add",method= RequestMethod.POST)
    @ApiOperation(value="添加公告信息")
    public ResultDTO notice_add(String name,String content) throws Exception {
        ResultDTO resultDTO=new ResultDTO();
        notice_getRequestDTO requestDTO = new notice_getRequestDTO();
        requestDTO.setName(name);
        requestDTO.setContent(content);
        requestDTO.setAccount("manager");
        requestDTO.setCreateTime(new Date());
        resultDTO=this.resourceServer.notice_add(requestDTO);

        return resultDTO;
    }

    @RequestMapping(value = "/check_item_get",method= RequestMethod.POST)
    @ApiOperation(value="获取审核条目信息")
    public PageResponseDTO<offworkapplicationEntity> check_item_get(String type,String ischecked,String condition, int pageNo, int pageSize) throws Exception {
        PageResponseDTO<offworkapplicationEntity> resultDTO=new PageResponseDTO<offworkapplicationEntity> ();
        SearchOffWorkApplicationByMasterRequestDTO requestDTO = new SearchOffWorkApplicationByMasterRequestDTO();
        if (type!="" && type!=null ){
            requestDTO.setApplicationType(type);
        }

        if (ischecked!=null){
            switch (ischecked){
                case "未审核":
                    requestDTO.setApprovalState("未审核");
                    break;
                case "审核通过":
                    requestDTO.setApprovalState("同意");
                    break;
                case "审核未通过":
                    requestDTO.setApprovalState("不同意");
                    break;
            }
        }

        if (condition==""){
            condition=null;
        }
        try{
            int id = Integer.parseInt(condition);
            requestDTO.setEmployeeId(id);
        }catch (Exception e){
            if (condition!=null){
                requestDTO.setCondition(condition);
            }
        }
        requestDTO.setPageNo(pageNo);
        requestDTO.setPageSize(pageSize);
        resultDTO=this.resourceServer.SearchOffWorkApplicationByMaster(requestDTO);

        return resultDTO;
    }

    @RequestMapping(value = "/check_item",method= RequestMethod.POST)
    @ApiOperation(value="审核条目")
    public ResultDTO check_item(Integer id, Integer result) throws Exception {
        ResultDTO resultDTO=new ResultDTO ();
        CheckOffWorkApplicationRequestDTO requestDTO = new CheckOffWorkApplicationRequestDTO();
        requestDTO.setId(id);
        switch (result){
            case 1:
                requestDTO.setApprovalResult("同意");
                resultDTO=this.resourceServer.CheckOffWorkApplication(requestDTO);
                break;
            case 2:
                requestDTO.setApprovalResult("不同意");
                resultDTO=this.resourceServer.CheckOffWorkApplication(requestDTO);
                break;
            default:
                resultDTO.setCode(Result.SYSERR.CODE);
                resultDTO.setMessage("参数错误");
                break;
        }

        return resultDTO;
    }

    @PostMapping("/logout")
    public void logout() throws Exception {
        ResultDTO resultDTO=new ResultDTO();
        if(session.getAttribute("user")!=null && session.getAttribute("user")!="") {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            request.getSession().removeAttribute("user");
            request.getSession().removeAttribute("psw");
        }
    }

    @PostMapping("/update_psw")
    public void update_psw(String loginUser, String psw_old,String psw_new) throws Exception {
        ResultDTO resultDTO=new ResultDTO();
        ManagerRequestDTO requestDTO=new ManagerRequestDTO();
        requestDTO.setAccountnumber(loginUser);
        requestDTO.setPassword(psw_old);
        requestDTO.setPsw_new(psw_new);
        resultDTO=this.resourceServer.update_psw_manager(requestDTO);
        if(session.getAttribute("user")!=null && session.getAttribute("user")!="") {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            request.getSession().removeAttribute("user");
            request.getSession().removeAttribute("psw");
        }
    }

    @PostMapping("/sign_state")
    public sign_dataResponseDTO sign_state() throws Exception {
        sign_dataResponseDTO responseDTO = new sign_dataResponseDTO();
        responseDTO=resourceServer.signStateLastSixMonths();
        return responseDTO;
    }

    @RequestMapping(value = "/SearchSignItem",method=RequestMethod.POST)
    @ApiOperation(value="查询签到记录")
    public PageResponseDTO<signEntity> SearchSignItem(String account, String type, Date date, boolean isForbidDate,int pageNo,int pageSize) throws Exception {
        SignRequestDTO requestDTO=new SignRequestDTO();
        if (!isForbidDate){
            Date starTime=new Date(date.getYear(),date.getMonth(),1);
            Date endTime;
            if (date.getMonth()==12){
                endTime=new Date(date.getYear()+1,1,1);
            }
            endTime=new Date(date.getYear(),date.getMonth()+1,1);
            requestDTO.setStartTime(starTime);
            requestDTO.setEndTime(endTime);
        }
        if (account!=null && account!=""){
            requestDTO.setAccountnumber(account);
        }
        requestDTO.setPageNo(pageNo);
        requestDTO.setPageSize(pageSize);
        if (!type.equals("0")){
            requestDTO.setException(type);
        }
        PageResponseDTO<signEntity> result= new PageResponseDTO<signEntity>();
        result = this.resourceServer.searchSignItem(requestDTO);

        return result;
    }
}
