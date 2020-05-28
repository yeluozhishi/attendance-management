package com.whk.resourceserver.service;

import com.whk.resourceserver.dto.common.PageResponseDTO;
import com.whk.resourceserver.dto.common.ResultDTO;
import com.whk.resourceserver.dto.request.*;
import com.whk.resourceserver.dto.response.AccountResponseDTO;
import com.whk.resourceserver.dto.response.EmployeeResponseDTO;
import com.whk.resourceserver.dto.response.SignDiaryResponseDTO;
import com.whk.resourceserver.dto.response.sign_dataResponseDTO;
import com.whk.resourceserver.entity.*;

public interface ResourceServer {

    /**
     * 登录
     * @param requestDTO
     * @return
     * @throws Exception
     */
    ResultDTO userLogin(UserLoginRequestDTO requestDTO) throws Exception;

    /**
     * 签到
     * @param requestDTO
     * @return
     * @throws Exception
     */
    ResultDTO signDiary(SignDiaryRequestDTO requestDTO) throws Exception;

    /**
     * 分页查询签到记录
     * @param requestDTO
     * @return
     * @throws Exception
     */
    PageResponseDTO<signEntity> searchSignItem(SignRequestDTO requestDTO) throws Exception;

    /**
     * 请假申请
     * @param requestDTO
     * @return
     * @throws Exception
     */
    ResultDTO OffWorkApplication(OffWorkApplicationRequestDTO requestDTO) throws Exception;

    /**
     * 分页查询请假申请
     * @param requestDTO
     * @return
     * @throws Exception
     */
    PageResponseDTO<offworkapplicationEntity> SearchOffWorkApplication(SearchOffWorkApplicationRequestDTO requestDTO) throws Exception;

    /**
     * 审核请假申请（管理）
     * @param requestDTO
     * @return
     * @throws Exception
     */
    ResultDTO CheckOffWorkApplication(CheckOffWorkApplicationRequestDTO requestDTO) throws Exception;

    /**
     * 搜索请假申请（管理）
     * @param requestDTO
     * @return
     * @throws Exception
     */
    PageResponseDTO<offworkapplicationEntity> SearchOffWorkApplicationByMaster(SearchOffWorkApplicationByMasterRequestDTO requestDTO) throws Exception;

    /**
     * 职员信息录入（管理）
     * @param requestDTO
     * @return
     * @throws Exception
     */
    ResultDTO EntryEmployee(EntryEmployeeRequestDTO requestDTO) throws Exception;

    /**
     * 职员信息删除（管理）
     * @param requestDTO
     * @return
     * @throws Exception
     */
    ResultDTO DeleteEmployeee(DeleteEmployeeRequestDTO requestDTO) throws Exception;

    /**
     * 职员信息更新（管理）
     * @param requestDTO
     * @return
     * @throws Exception
     */
    ResultDTO UpdateEmployee(UpdateEmployeeRequestDTO requestDTO) throws Exception;

    /**
     * 获取员工信息（管理）
     * @param requestDTO
     * @return
     * @throws Exception
     */
    PageResponseDTO<employeeEntity> GetEmployee(SearchEmployeeRequestDTO requestDTO) throws Exception;

    /**
     * 查找员工信息
     * @param requestDTO
     * @return
     * @throws Exception
     */
    EmployeeResponseDTO SearchEmployeeInfo(AccountRequestDTO requestDTO) throws Exception;

    /**
     * 备忘录查找
     * @param requestDTO
     * @return
     * @throws Exception
     */
    PageResponseDTO<memorandumEntity> memorandum_search(MemorandumRequestDTO requestDTO) throws Exception;

    /**
     * 备忘录保存
     * @param requestDTO
     * @return
     * @throws Exception
     */
    ResultDTO memorandum_save(MemorandumRequestDTO requestDTO) throws Exception;

    /**
     * 签到数据处理
     * @throws Exception
     * @return
     */
    ResultDTO sign_deal() throws Exception;

    /**
     * 打卡状态
     * @param requestDTO
     * @return
     */
    SignDiaryResponseDTO signState(signStateRequestDTO requestDTO) throws Exception;



    /**
     *管理员登录
     * @param requestDTO
     * @return
     * @throws Exception
     */
    ResultDTO administrators_login(ManagerRequestDTO requestDTO) throws Exception;

    /**
     * 获取账户(管理)
     * @param requestDTO
     * @return
     * @throws Exception
     */
    AccountResponseDTO SearchAccountInfo(AccountRequestDTO requestDTO) throws Exception;

    /**
     * 添加职员账户(管理)
     * @param requestDTO
     * @return
     * @throws Exception
     */
    ResultDTO AddAccount(AccountRequestDTO requestDTO) throws Exception;

    /**
     * 删除账户(管理)
     * @param requestDTO
     * @return
     * @throws Exception
     */
    ResultDTO DeleteAccount(AccountRequestDTO requestDTO) throws Exception;;

    /**
     * 添加职员(管理)
     * @param requestDTO
     * @return
     * @throws Exception
     */
    ResultDTO AddEmployee(UpdateEmployeeRequestDTO requestDTO) throws Exception;

    /**
     * 任务发布(管理)
     * @param requestDTO
     * @return
     * @throws Exception
     */
    ResultDTO notice_add(notice_getRequestDTO requestDTO) throws Exception;

    /**
     * 公告获取(管理)
     * @param requestDTO
     * @return
     * @throws Exception
     */
    PageResponseDTO notice_get(notice_getRequestDTO requestDTO) throws Exception;

    /**
     * 公告删除(管理)
     * @param requestDTO
     * @return
     * @throws Exception
     */
    ResultDTO notice_delete(notice_getRequestDTO requestDTO) throws Exception;

    /**
     * 公告更新(管理)
     * @param requestDTO
     * @return
     * @throws Exception
     */
    ResultDTO notice_update(notice_getRequestDTO requestDTO) throws Exception;

    /**
     * 条件查询职员(管理)
     * @param requestDTO
     * @return
     */
    PageResponseDTO<employeeEntity> SearchEmployee(SearchEmployeeRequestDTO requestDTO) throws Exception;

    /**
     * 修改密码(管理)
     * @param requestDTO
     * @return
     * @throws Exception
     */
    ResultDTO update_psw_manager(ManagerRequestDTO requestDTO) throws Exception;

    /**
     * 修改密码
     * @param requestDTO
     * @return
     * @throws Exception
     */
    ResultDTO update_psw(AccountRequestDTO requestDTO) throws Exception;

    /**
     * 获取上月签到分析数据
     * @throws Exception
     * @return
     */
    sign_dataResponseDTO sign_data() throws Exception;

    /**
     * 获取前六个月签到异常数和正常数
     * @return
     */
    sign_dataResponseDTO signStateLastSixMonths() throws Exception;
}
