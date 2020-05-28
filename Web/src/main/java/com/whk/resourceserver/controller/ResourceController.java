package com.whk.resourceserver.controller;

import com.whk.resourceserver.dto.common.PageResponseDTO;
import com.whk.resourceserver.dto.common.ResultDTO;
import com.whk.resourceserver.dto.request.*;
import com.whk.resourceserver.dto.response.EmployeeResponseDTO;
import com.whk.resourceserver.dto.response.SignDiaryResponseDTO;
import com.whk.resourceserver.entity.*;
import com.whk.resourceserver.service.ResourceServer;
import com.whk.resourceserver.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.TimeZone;

@RestController
@RequestMapping("/rest/Controller")
@Api(description = "资源服务接口")
public class ResourceController {
    //jackson设置时区
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
        return jacksonObjectMapperBuilder ->
                jacksonObjectMapperBuilder.timeZone(TimeZone.getTimeZone("GMT+8"));
    }


    private static final Logger LOG = LoggerFactory.getLogger(ResourceController.class);

    //资源服务接口
    @Autowired
    private ResourceServer resourceServer;

    @ResponseBody
    @RequestMapping(value = "/userLogin",method= RequestMethod.POST)
    @ApiOperation(value="登录")
    public ResultDTO userLogin(@Valid @RequestBody UserLoginRequestDTO requestDTO,
                               BindingResult bindingResult){
        ResultDTO result=new ResultDTO();

        try {
            // 判断参数是否合法
            if (bindingResult.hasErrors()){
                result.setCode(Result.PARAMERR.CODE);
                result.setMessage(bindingResult.getFieldError().getDefaultMessage());
                return result;
            }

            result = this.resourceServer.userLogin(requestDTO);
        } catch (Exception e) {
            LOG.error("登录方法异常！原因："+e.getMessage());
            result.setCode(Result.SYSERR.CODE);
            result.setMessage(Result.SYSERR.MSG);
        }
        return result;
    }

    @RequestMapping(value = "/sign",method=RequestMethod.POST)
    @ApiOperation(value="签到")
    public ResultDTO sign(@Valid @RequestBody SignDiaryRequestDTO requestDTO,
                               BindingResult bindingResult){
        ResultDTO result=new ResultDTO();

        try {
            // 判断参数是否合法
            if (bindingResult.hasErrors()){
                result.setCode(Result.PARAMERR.CODE);
                result.setMessage(bindingResult.getFieldError().getDefaultMessage());
                return result;
            }

            result = this.resourceServer.signDiary(requestDTO);
        } catch (Exception e) {
            LOG.error("签到方法异常！原因："+e.getMessage());
            result.setCode(Result.SYSERR.CODE);
            result.setMessage(Result.SYSERR.MSG);
        }
        return result;
    }

    //由定时任务启动签到处理：SaticScheduleTask
    //此处手动启动
    @RequestMapping(value = "/sign_deal",method=RequestMethod.POST)
    @ApiOperation(value="签到处理")
    public ResultDTO sign_deal(){
        ResultDTO result=new ResultDTO();

        try {
            result = this.resourceServer.sign_deal();
        } catch (Exception e) {
            LOG.error("签到处理异常！原因："+e.getMessage());
            result.setCode(Result.SYSERR.CODE);
            result.setMessage(Result.SYSERR.MSG);
        }
        return result;
    }

    @RequestMapping(value = "/signState",method=RequestMethod.POST)
    @ApiOperation(value="签到状态")
    public ResultDTO signState(@Valid @RequestBody signStateRequestDTO requestDTO,
                          BindingResult bindingResult){
        SignDiaryResponseDTO result= new SignDiaryResponseDTO();

        try {
            // 判断参数是否合法
            if (bindingResult.hasErrors()){
                result.setCode(Result.PARAMERR.CODE);
                result.setMessage(bindingResult.getFieldError().getDefaultMessage());
                return result;
            }
            result = this.resourceServer.signState(requestDTO);
        } catch (Exception e) {
            LOG.error("签到状态方法异常！原因："+e.getMessage());
            result.setCode(Result.SYSERR.CODE);
            result.setMessage(Result.SYSERR.MSG);
        }
        return result;
    }

    @RequestMapping(value = "/SearchSignItem",method=RequestMethod.POST)
    @ApiOperation(value="查询签到记录")
    public PageResponseDTO<signEntity> SearchSignItem(@Valid @RequestBody SignRequestDTO requestDTO,
                                                      BindingResult bindingResult){
        PageResponseDTO<signEntity> result= new PageResponseDTO<signEntity>();

        try {
            // 判断参数是否合法
            if (bindingResult.hasErrors()){
                result.setCode(Result.PARAMERR.CODE);
                result.setMessage(bindingResult.getFieldError().getDefaultMessage());
                return result;
            }
            result = this.resourceServer.searchSignItem(requestDTO);
        } catch (Exception e) {
            LOG.error("查询签到记录方法异常！原因："+e.getMessage());
            result.setCode(Result.SYSERR.CODE);
            result.setMessage(Result.SYSERR.MSG);
        }
        return result;
    }

    @RequestMapping(value = "/SearchOffWorkApplication",method=RequestMethod.POST)
    @ApiOperation(value="搜索请假申请")
    public PageResponseDTO<offworkapplicationEntity> SearchOffWorkApplication(@Valid @RequestBody SearchOffWorkApplicationRequestDTO requestDTO,
                                                                              BindingResult bindingResult){
        PageResponseDTO<offworkapplicationEntity> result=new PageResponseDTO<offworkapplicationEntity>();

        try {
            // 判断参数是否合法
            if (bindingResult.hasErrors()){
                result.setCode(Result.PARAMERR.CODE);
                result.setMessage(bindingResult.getFieldError().getDefaultMessage());
                return result;
            }
            result = this.resourceServer.SearchOffWorkApplication(requestDTO);
        } catch (Exception e) {
            LOG.error("搜索请假申请方法异常！原因："+e.getMessage());
            result.setCode(Result.SYSERR.CODE);
            result.setMessage(Result.SYSERR.MSG);
        }
        return result;
    }

    @RequestMapping(value = "/OffWorkApplication",method=RequestMethod.POST)
    @ApiOperation(value="创建请假申请或修改（设置id为修改）")
    public ResultDTO OffWorkApplication(@Valid @RequestBody OffWorkApplicationRequestDTO requestDTO,
                                        BindingResult bindingResult){
        ResultDTO result= new ResultDTO();

        try {
            // 判断参数是否合法
            if (bindingResult.hasErrors()){
                result.setCode(Result.PARAMERR.CODE);
                result.setMessage(bindingResult.getFieldError().getDefaultMessage());
                return result;
            }
            result = this.resourceServer.OffWorkApplication(requestDTO);
        } catch (Exception e) {
            LOG.error("创建请假申请方法异常！原因："+e.getMessage());
            result.setCode(Result.SYSERR.CODE);
            result.setMessage(Result.SYSERR.MSG);
        }
        return result;
    }

    @RequestMapping(value = "/SearchEmployeeInfo",method=RequestMethod.POST)
    @ApiOperation(value="查找职员信息")
    public EmployeeResponseDTO SearchEmployeeInfo(
            @Valid @RequestBody AccountRequestDTO requestDTO,
            BindingResult bindingResult){
        EmployeeResponseDTO result=new EmployeeResponseDTO();

        try {
            // 判断参数是否合法
            if (bindingResult.hasErrors()){
                result.setCode(Result.PARAMERR.CODE);
                result.setMessage(bindingResult.getFieldError().getDefaultMessage());
                return result;
            }
            result = this.resourceServer.SearchEmployeeInfo(requestDTO);
        } catch (Exception e) {
            LOG.error("查找职员信息方法异常！原因："+e.getMessage());
            result.setCode(Result.SYSERR.CODE);
            result.setMessage(Result.SYSERR.MSG);
        }
        return result;
    }

    @RequestMapping(value = "/memorandum_search",method=RequestMethod.POST)
    @ApiOperation(value="备忘录查询")
    public PageResponseDTO<memorandumEntity> memorandum_search(
            @Valid @RequestBody MemorandumRequestDTO requestDTO,
            BindingResult bindingResult){
        PageResponseDTO<memorandumEntity> result=new PageResponseDTO<>();

        try {
            // 判断参数是否合法
            if (bindingResult.hasErrors()){
                result.setCode(Result.PARAMERR.CODE);
                result.setMessage(bindingResult.getFieldError().getDefaultMessage());
                return result;
            }
            result = this.resourceServer.memorandum_search(requestDTO);
        } catch (Exception e) {
            LOG.error("备忘录查询方法异常！原因："+e.getMessage());
            result.setCode(Result.SYSERR.CODE);
            result.setMessage(Result.SYSERR.MSG);
        }
        return result;
    }

    @RequestMapping(value = "/memorandum_save",method=RequestMethod.POST)
    @ApiOperation(value="备忘录保存")
    public ResultDTO memorandum_save(
            @Valid @RequestBody MemorandumRequestDTO requestDTO,
            BindingResult bindingResult){
        ResultDTO result=new ResultDTO();
        try {
            // 判断参数是否合法
            if (bindingResult.hasErrors()){
                result.setCode(Result.PARAMERR.CODE);
                result.setMessage(bindingResult.getFieldError().getDefaultMessage());
                return result;
            }
            result = this.resourceServer.memorandum_save(requestDTO);
        } catch (Exception e) {
            LOG.error("备忘录保存方法异常！原因："+e.getMessage());
            result.setCode(Result.SYSERR.CODE);
            result.setMessage(Result.SYSERR.MSG);
        }
        return result;
    }

    @RequestMapping(value = "/notice_get",method=RequestMethod.POST)
    @ApiOperation(value="公告获取")
    public PageResponseDTO notice_get(
            @Valid @RequestBody notice_getRequestDTO requestDTO,
            BindingResult bindingResult){
        PageResponseDTO result=new PageResponseDTO();
        try {
            // 判断参数是否合法
            if (bindingResult.hasErrors()){
                result.setCode(Result.PARAMERR.CODE);
                result.setMessage(bindingResult.getFieldError().getDefaultMessage());
                return result;
            }
            result = this.resourceServer.notice_get(requestDTO);
        } catch (Exception e) {
            LOG.error("任务获取！原因："+e.getMessage());
            result.setCode(Result.SYSERR.CODE);
            result.setMessage(Result.SYSERR.MSG);
        }
        return result;
    }

    @RequestMapping(value = "/update_psw",method=RequestMethod.POST)
    @ApiOperation(value="修改密码")
    public ResultDTO update_psw(
            @Valid @RequestBody AccountRequestDTO requestDTO,
            BindingResult bindingResult){
        ResultDTO result=new ResultDTO();
        try {
            // 判断参数是否合法
            if (bindingResult.hasErrors()){
                result.setCode(Result.PARAMERR.CODE);
                result.setMessage(bindingResult.getFieldError().getDefaultMessage());
                return result;
            }
            result = this.resourceServer.update_psw(requestDTO);
        } catch (Exception e) {
            LOG.error("任务获取！原因："+e.getMessage());
            result.setCode(Result.SYSERR.CODE);
            result.setMessage(Result.SYSERR.MSG);
        }
        return result;
    }

}

