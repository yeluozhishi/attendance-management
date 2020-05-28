package com.whk.resourceserver.service.ipml;

import com.whk.resourceserver.dto.common.PageResponseDTO;
import com.whk.resourceserver.dto.common.ResultDTO;
import com.whk.resourceserver.dto.request.*;
import com.whk.resourceserver.dto.response.AccountResponseDTO;
import com.whk.resourceserver.dto.response.EmployeeResponseDTO;
import com.whk.resourceserver.dto.response.SignDiaryResponseDTO;
import com.whk.resourceserver.dto.response.sign_dataResponseDTO;
import com.whk.resourceserver.entity.*;
import com.whk.resourceserver.repository.*;
import com.whk.resourceserver.service.ResourceServer;
import com.whk.resourceserver.util.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ResourceServerIpml implements ResourceServer {

    //账户
    @Autowired
    private AccountEntityRepository accountEntityRepository;

    //签到
    @Autowired
    private SignEntityRepository signEntityRepository;

    //请假申请
    @Autowired
    private OffWorkApplicationEntityRepository offWorkApplicationEntityRepository;

    //职员信息
    @Autowired
    private EmployeeEntityRepository employeeEntityRepository;

    //登陆记录
    @Autowired
    private DiaryEntityRepository diaryEntityRepository;

    //签到记录
    @Autowired
    private SignDiaryEntityRepository signDiaryEntityRepository;

    //备忘录
    @Autowired
    private MemorandumEntityRepository memorandumEntityRepository;

    //任务
    @Autowired
    private NoticeEntityRepository noticeEntityRepository;

    //管理员账户
    @Autowired
    private ManagerEntityRepository managerEntityRepository;

    @Transactional
    @Override
    public ResultDTO userLogin(UserLoginRequestDTO requestDTO) throws Exception {
        ResultDTO result=new ResultDTO();
        accountEntity accountEntity=accountEntityRepository.findUser(requestDTO.getAccountnumber(),requestDTO.getPassword());
        if (accountEntity!=null){
            diaryEntity diaryEntity=new diaryEntity();
            diaryEntity.setEmployeeId(accountEntity.getEmployeeId());
            diaryEntity.setLoginTime(new Date());
            diaryEntityRepository.save(diaryEntity);
            result.setCode(Result.SUCCESS.CODE);
            result.setMessage("登录成功");
        }else {
            result.setCode(Result.SYSERR.CODE);
            result.setMessage("登录失败");
        }
        return result;
    }

    @Override
    public SignDiaryResponseDTO signDiary(SignDiaryRequestDTO requestDTO) throws Exception {
        signDiaryEntity signDiaryEntity = new signDiaryEntity();
        SignDiaryResponseDTO result=new SignDiaryResponseDTO();

        //现在时间
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(today);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(dateString, pos);
        int number = signDiaryEntityRepository.findToday(requestDTO.getAccountnumber(),strtodate);
        //当日签到次数限制
        if(number<=8){
            signDiaryEntity.setAccountnumber(requestDTO.getAccountnumber());
            signDiaryEntity.setSignTime(today);
            signDiaryEntityRepository.save(signDiaryEntity);
            result.setCode(Result.SUCCESS.CODE);
            result.setMSG(Result.SUCCESS.MSG);
            result.setSignTime(today);
            result.setMessage("签到成功");
        }else {
            result.setCode(Result.PARAMERR.CODE);
            result.setMSG(Result.PARAMERR.MSG);
            result.setSignTime(today);
            result.setMessage("签到失败");
        }
        return result;
    }

    @Override
    public SignDiaryResponseDTO signState(signStateRequestDTO requestDTO) throws Exception {
        SignDiaryResponseDTO responseDTO=new SignDiaryResponseDTO();
        //现在时间
        Date today = new Date();
        //今日00：00：00
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(today);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(dateString, pos);
        try{
            List<signDiaryEntity> list = signDiaryEntityRepository.findState(requestDTO.getAccountnumber(),strtodate);

            switch (list.size()){
                case 0:
                    responseDTO.setMSG("未签到");
                    break;
                case 1:
                    responseDTO.setSignTime_first(list.get(0).getSignTime());
                    break;

                default:
                    responseDTO.setSignTime_last(list.get(0).getSignTime());
                    responseDTO.setSignTime_first(list.get(list.size()-1).getSignTime());
                    break;
            }

            responseDTO.setCode(Result.SUCCESS.CODE);
            responseDTO.setMessage(Result.SUCCESS.MSG);
        }catch (Exception e){
            System.out.println(e);
            responseDTO.setCode(Result.SYSERR.CODE);
            responseDTO.setMessage(Result.SYSERR.MSG);
        }
        return responseDTO;
    }

    @Override
    public ResultDTO administrators_login(ManagerRequestDTO requestDTO) throws Exception {
        ResultDTO resultDTO=new ResultDTO();
        managerEntity managerEntity=new managerEntity();
        managerEntity=managerEntityRepository.findByIdAndPSW(requestDTO.getAccountnumber(),requestDTO.getPassword());
        if (managerEntity.getAccountnumber()!=null && managerEntity.getPassword()!=null){
            resultDTO.setCode(Result.SUCCESS.CODE);
            resultDTO.setMessage(Result.SUCCESS.MSG);
            return resultDTO;
        }else {
            resultDTO.setCode(Result.SYSERR.CODE);
            resultDTO.setMessage(Result.SYSERR.MSG);
            return resultDTO;
        }
    }

    @Override
    public AccountResponseDTO SearchAccountInfo(AccountRequestDTO requestDTO) throws Exception {
        AccountResponseDTO responseDTO= new AccountResponseDTO();
        accountEntity accountEntity=accountEntityRepository.findOneByEmployeeId(requestDTO.getEmployeeId());
        if (accountEntity!=null){
            responseDTO.setAccountEntity(accountEntity);
            responseDTO.setCode(Result.SUCCESS.CODE);
            responseDTO.setMessage(Result.SUCCESS.MSG);
        }else {
            responseDTO.setCode(Result.SYSERR.CODE);
            responseDTO.setMessage(Result.SYSERR.MSG);
        }
        return responseDTO;
    }

    @Override
    public ResultDTO AddAccount(AccountRequestDTO requestDTO) throws Exception {
        accountEntity accountEntity=new accountEntity();
        ResultDTO resultDTO=new ResultDTO();

        accountEntity search=accountEntityRepository.findOneBYAccount(requestDTO.getAccountnumber());
        if (search!=null){
            resultDTO.setCode(Result.SYSERR.CODE);
            resultDTO.setMessage("已存在");
        }else {
            accountEntity.setAccountnumber(requestDTO.getAccountnumber());
            accountEntity.setEmployeeId(requestDTO.getEmployeeId());
            accountEntity.setPassword(requestDTO.getPassword());
            accountEntity.setCreateTime(new Date());
            accountEntityRepository.save(accountEntity);
            resultDTO.setCode(Result.SUCCESS.CODE);
            resultDTO.setMessage(Result.SUCCESS.MSG);
        }
        return resultDTO;
    }

    @Override
    public ResultDTO DeleteAccount(AccountRequestDTO requestDTO) throws Exception {
        ResultDTO resultDTO=new ResultDTO();
        accountEntity accountEntity;
        accountEntity=accountEntityRepository.findOneByEmployeeId(requestDTO.getEmployeeId());
        if (accountEntity!=null){
            accountEntityRepository.delete(accountEntity);
            resultDTO.setCode(Result.SUCCESS.CODE);
            resultDTO.setMessage(Result.SUCCESS.MSG);
        }else {
            resultDTO.setCode(Result.SYSERR.CODE);
            resultDTO.setMessage(Result.SYSERR.MSG);
        }
        return resultDTO;
    }

    @Override
    public ResultDTO AddEmployee(UpdateEmployeeRequestDTO requestDTO) throws Exception {
        ResultDTO resultDTO=new ResultDTO();
        employeeEntity employeeEntity=new employeeEntity();
        employeeEntity.setName(requestDTO.getName());
        employeeEntity.setSex(requestDTO.getSex());
        employeeEntity.setAge(requestDTO.getAge());
        employeeEntity.setPosition(requestDTO.getPosition());
        employeeEntity.setSalary(requestDTO.getSalary());
        employeeEntity.setEntryTime(new Date());
        try{
            employeeEntityRepository.save(employeeEntity);
            resultDTO.setCode(Result.SUCCESS.CODE);
            resultDTO.setMessage(Result.SUCCESS.MSG);
        }catch (Exception e){
            resultDTO.setCode(Result.SYSERR.CODE);
            resultDTO.setMessage(Result.SYSERR.MSG);
        }
        return resultDTO;
    }

    @Override
    public PageResponseDTO<signEntity> searchSignItem(SignRequestDTO requestDTO) throws Exception {
        Pageable pageable = PageRequest.of (requestDTO.getPageNo()-1,requestDTO.getPageSize());
        PageResponseDTO<signEntity> result=new PageResponseDTO<signEntity>();

        //条件分页查找签到记录
        Page signListPage=signEntityRepository.findbyMaster(requestDTO.getAccountnumber(),requestDTO.getStartTime(),
                requestDTO.getEndTime(),requestDTO.getException(),pageable);
        result.setContent(signListPage.getContent());
        result.setPageNo(requestDTO.getPageNo());
        result.setPageSize(requestDTO.getPageSize());
        result.setTotalElement(signListPage.getTotalElements());
        result.setTotalPages(signListPage.getTotalPages());

        return result;
    }

    @Override
    public ResultDTO OffWorkApplication(OffWorkApplicationRequestDTO requestDTO) throws Exception {
        offworkapplicationEntity result=new offworkapplicationEntity();
        ResultDTO resultDTO=new ResultDTO();
        if (requestDTO.getId()!=null){

            offworkapplicationEntity offworkapplicationEntity =offWorkApplicationEntityRepository.findByIdAndEmployeeId(requestDTO.getEmployeeId(),requestDTO.getId());
            if (offworkapplicationEntity !=null && offworkapplicationEntity.getReviewTime()!=null){
                resultDTO.setCode(Result.SYSERR.CODE);
                resultDTO.setMessage("已审核完成，不可修改。");
                return resultDTO;
            }
        }
        result.setEmployeeId(requestDTO.getEmployeeId());
        result.setCreateTime(new Date());
        result.setApplicationType(requestDTO.getApplicationType());
        result.setApplicationReason(requestDTO.getApplicationReason());
        result.setStartTime(requestDTO.getStartTime());
        result.setEndTime(requestDTO.getEndTime());
        try{
            //保存
            offWorkApplicationEntityRepository.save(result);
            resultDTO.setCode(Result.SUCCESS.CODE);
            resultDTO.setMessage(Result.SUCCESS.MSG);
        }catch (Exception e){
            System.out.println(e);
            resultDTO.setCode(Result.SYSERR.CODE);
            resultDTO.setMessage(Result.SYSERR.MSG);
        }
        return resultDTO;
    }

    @Override
    public PageResponseDTO<offworkapplicationEntity> SearchOffWorkApplication(SearchOffWorkApplicationRequestDTO requestDTO) throws Exception {
        PageResponseDTO<offworkapplicationEntity> responseDTO=new PageResponseDTO<offworkapplicationEntity>();
        Pageable pageable= PageRequest.of((requestDTO.getPageNo()-1),requestDTO.getPageSize());

        Page applicationList=offWorkApplicationEntityRepository.find(requestDTO.getEmployeeId(),requestDTO.getApplicationType(),
                requestDTO.getStartTime(),requestDTO.getEndTime(),pageable);
        responseDTO.setTotalPages(applicationList.getTotalPages());
        responseDTO.setTotalElement(applicationList.getTotalElements());
        responseDTO.setPageNo(applicationList.getPageable().getPageNumber());
        responseDTO.setPageSize(applicationList.getContent().size());
        responseDTO.setContent(applicationList.getContent());
        return responseDTO;
    }

    @Override
    public ResultDTO CheckOffWorkApplication(CheckOffWorkApplicationRequestDTO requestDTO) throws Exception {
        ResultDTO resultDTO=new ResultDTO();
        try{
            String Id=requestDTO.getId().toString();

            Specification<offworkapplicationEntity> spe = (Specification<offworkapplicationEntity>) (root, criteriaQuery, criteriaBuilder) -> {
                Path<Object> id = root.get("id");
                Predicate predicate = criteriaBuilder.equal(id,requestDTO.getId());
                return predicate;
            };
            offworkapplicationEntity offworkapplicationEntity =offWorkApplicationEntityRepository.findOne(spe).get();

            offworkapplicationEntity.setReviewTime(new Date());
            offworkapplicationEntity.setReviewResult(requestDTO.getApprovalResult());

            offWorkApplicationEntityRepository.save(offworkapplicationEntity);

            if (offworkapplicationEntity.getApplicationType().equals("补签")){
                signEntity signEntity=new signEntity();

                //开始时间
                Date time=offworkapplicationEntity.getStartTime();

                //格式化时间yyyy-MM-dd 00:00:00
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formatter.format(time);
                ParsePosition pos = new ParsePosition(0);
                Date strtodate = formatter.parse(dateString, pos);//昨日00：00：00

                //次日00：00：00
                Date today = new Date(strtodate.getTime()+24*60*60*1000L);

                accountEntity accountEntity=accountEntityRepository.findOneByEmployeeId(offworkapplicationEntity.getEmployeeId());

                signEntity=signEntityRepository.findByTimeAndAccount(accountEntity.getAccountnumber(),strtodate,today);

                if (signEntity!=null){
                    signEntity.setSigninTime(offworkapplicationEntity.getStartTime());
                    signEntity.setSignoutTime(offworkapplicationEntity.getEndTime());
                    signEntity.setExceptionDetail("已更正");
                    signEntityRepository.save(signEntity);
                }
            }

            resultDTO.setCode(Result.SUCCESS.CODE);
            resultDTO.setMessage("审核完成！");
        }catch (Exception e){
            System.out.println(e);
            resultDTO.setCode(Result.SYSERR.CODE);
            resultDTO.setMessage(Result.SYSERR.MSG);
        }
        return resultDTO;
    }

    @Override
    public PageResponseDTO<offworkapplicationEntity> SearchOffWorkApplicationByMaster(SearchOffWorkApplicationByMasterRequestDTO requestDTO) throws Exception {
        Pageable pageable = PageRequest.of((requestDTO.getPageNo()-1),requestDTO.getPageSize());
        PageResponseDTO<offworkapplicationEntity> responseDTO=new PageResponseDTO<offworkapplicationEntity>();
        Page applicationList;
        if (requestDTO.getApprovalState()=="未审核"){
            applicationList=offWorkApplicationEntityRepository.findByMaster(requestDTO.getEmployeeId(),requestDTO.getApplicationType(),
                    requestDTO.getCondition(),pageable);
        }else {
            applicationList=offWorkApplicationEntityRepository.findByMaster(requestDTO.getEmployeeId(),
                    requestDTO.getApprovalState(),requestDTO.getApplicationType(),requestDTO.getCondition(),pageable);
        }

        responseDTO.setTotalPages(applicationList.getTotalPages());
        responseDTO.setTotalElement(applicationList.getTotalElements());
        responseDTO.setPageNo(applicationList.getPageable().getPageNumber());
        responseDTO.setPageSize(applicationList.getContent().size());
        responseDTO.setContent(applicationList.getContent());
        return responseDTO;
    }

    @Override
    public ResultDTO EntryEmployee(EntryEmployeeRequestDTO requestDTO) throws Exception {
        ResultDTO resultDTO=new ResultDTO();
        try{
            employeeEntity employeeEntity=new employeeEntity();
            employeeEntity.setName(requestDTO.getName());
            employeeEntity.setSex(requestDTO.getSex());
            employeeEntity.setAge(requestDTO.getAge());
            employeeEntity.setEntryTime(new Date());
            employeeEntity.setPosition(requestDTO.getPosition());
            employeeEntity.setSalary(requestDTO.getSalary());
            employeeEntityRepository.save(employeeEntity);
            resultDTO.setCode(Result.SUCCESS.CODE);
            resultDTO.setMessage("录入完成！");
        }catch (Exception e){
            System.out.println(e);
            resultDTO.setCode(Result.SYSERR.CODE);
            resultDTO.setMessage("录入失败！");
        }
        return resultDTO;
    }

    @Override
    public ResultDTO DeleteEmployeee(DeleteEmployeeRequestDTO requestDTO) throws Exception {
        ResultDTO resultDTO=new ResultDTO();
        employeeEntity employeeEntity=new employeeEntity();
        employeeEntity.setEmployeeId(requestDTO.getEmployeeId());
        try{
            employeeEntityRepository.delete(employeeEntity);
            resultDTO.setCode(Result.SUCCESS.CODE);
            resultDTO.setMessage("删除成功！");
        }catch (Exception e){
            System.out.println(e);
            resultDTO.setCode(Result.SYSERR.CODE);
            resultDTO.setMessage("删除失败！");
        }
        return resultDTO;
    }

    @Override
    public ResultDTO UpdateEmployee(UpdateEmployeeRequestDTO requestDTO) throws Exception {
        ResultDTO resultDTO=new ResultDTO();
        employeeEntity employee = new employeeEntity();
        employee.setEmployeeId(requestDTO.getEmployeeId());
        //匿名内部类
        /**
         * 自定义查询条件
         *      1.实现Specification接口（提供泛型：查询的对象类型）
         *      2.实现toPredicate方法（构造查询条件）
         *      3.需要借助方法参数中的两个参数（
         *          root：获取需要查询的对象属性
         *          CriteriaBuilder：构造查询条件的，内部封装了很多的查询条件（模糊匹配，精准匹配）
         *       ）
         *  案例：根据客户名称查询，查询客户名为传智播客的客户
         *          查询条件
         *              1.查询方式
         *                  cb对象
         *              2.比较的属性名称
         *                  root对象
         *
         */
//        Specification<Customer> spec = new Specification<Customer>() {
//            @Override
//            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                //1.获取比较的属性
//                Path<Object> custId = root.get("custId");//查询的式属性名，不是表的字段名
//                //2.构造查询条件  ：    select * from cst_customer where cust_id = 3
//                /**
//                 * 第一个参数：需要比较的属性（path对象）
//                 * 第二个参数：当前需要比较的取值
//                 */
//                Predicate predicate = cb.equal(custId, 3);//进行精准的匹配  （比较的属性，比较的属性的取值）
//                return predicate;
//            }
//        };
        Specification<employeeEntity> spe = new Specification<employeeEntity>() {
            @Override
            public Predicate toPredicate(Root<employeeEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> employeeId = root.get("employeeId");
                Predicate predicate = criteriaBuilder.equal(employeeId,requestDTO.getEmployeeId());
                return predicate;
            }
        };
        employeeEntity employeeEntity=employeeEntityRepository.findOne(spe).get();

        if (employeeEntity==null){
            resultDTO.setCode(Result.SYSERR.CODE);
            resultDTO.setMessage("修改失败！");
            return resultDTO;
        }else {
            if(requestDTO.getAge()!=null){
                employeeEntity.setAge(requestDTO.getAge());
            }
            if(requestDTO.getName()!=null){
                employeeEntity.setName(requestDTO.getName());
            }
            if(requestDTO.getPosition()!=null){
                employeeEntity.setPosition(requestDTO.getPosition());
            }
            if(requestDTO.getSalary()!=null){
                employeeEntity.setSalary(requestDTO.getSalary());
            }
            if(!Character.isSpaceChar(requestDTO.getSex())){
                employeeEntity.setName(requestDTO.getName());
            }
            if (requestDTO.getEntryTime()!=null){
                employeeEntity.setEntryTime(requestDTO.getEntryTime());
            }
            if (requestDTO.getQuitTime()!=null){
                employeeEntity.setQuitTime(requestDTO.getQuitTime());
            }

            try{
                employeeEntityRepository.save(employeeEntity);
                resultDTO.setCode(Result.SUCCESS.CODE);
                resultDTO.setMessage("修改成功！");
            }catch (Exception e){
                System.out.println(e);
                resultDTO.setCode(Result.SYSERR.CODE);
                resultDTO.setMessage("修改失败！");
            }
        }
        return resultDTO;
    }

    @Override
    public PageResponseDTO<employeeEntity> GetEmployee(SearchEmployeeRequestDTO requestDTO) throws Exception {
        PageResponseDTO<employeeEntity> responseDTO=new PageResponseDTO<employeeEntity>();
        Pageable pageable=PageRequest.of(requestDTO.getPageNo()-1,requestDTO.getPageSize());
        Page pageList=employeeEntityRepository.getEmployee(requestDTO.getEmployeeId(), requestDTO.getCondition(), pageable);
        responseDTO.setPageNo(requestDTO.getPageNo());
        responseDTO.setPageSize(requestDTO.getPageSize());
        responseDTO.setTotalElement(pageList.getTotalElements());
        responseDTO.setTotalPages(pageList.getTotalPages());
        responseDTO.setContent(pageList.getContent());

        return responseDTO;
    }

    @Override
    public EmployeeResponseDTO SearchEmployeeInfo(AccountRequestDTO requestDTO) throws Exception {
        EmployeeResponseDTO responseDTO=new EmployeeResponseDTO();
        employeeEntity employeeEntity=new employeeEntity();
        accountEntity accountEntity = new accountEntity();
        if (!requestDTO.getAccountnumber().isEmpty() || (requestDTO.getEmployeeId()!=null && requestDTO.getEmployeeId()>0)){
            if (!requestDTO.getAccountnumber().isEmpty()){
                Specification<accountEntity> specification = new Specification<com.whk.resourceserver.entity.accountEntity>() {
                    @Override
                    public Predicate toPredicate(Root<com.whk.resourceserver.entity.accountEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                        Path<Object> account = root.get("accountnumber");
                        Predicate predicate = criteriaBuilder.equal(account,requestDTO.getAccountnumber());
                        return predicate;
                    }
                };
                accountEntity = accountEntityRepository.findOne(specification).get();
                if (accountEntity!=null){
                    employeeEntity=employeeEntityRepository.findByEmployeeId(accountEntity.getEmployeeId());
                    responseDTO.setCode(Result.SUCCESS.CODE);
                    responseDTO.setMessage(Result.SUCCESS.MSG);
                }else {
                    responseDTO.setCode(Result.PARAMERR.CODE);
                    responseDTO.setMessage(Result.PARAMERR.MSG);
                }

            }else {
                Specification<accountEntity> specification = new Specification<com.whk.resourceserver.entity.accountEntity>() {
                    @Override
                    public Predicate toPredicate(Root<com.whk.resourceserver.entity.accountEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                        Path<Object> employeeId = root.get("employeeId");
                        Predicate predicate = criteriaBuilder.equal(employeeId, requestDTO.getEmployeeId());
                        return predicate;
                    }
                };
                accountEntity = accountEntityRepository.findOne(specification).get();
                if (accountEntity != null) {
                    employeeEntity = employeeEntityRepository.findByEmployeeId(accountEntity.getEmployeeId());
                    responseDTO.setCode(Result.SUCCESS.CODE);
                    responseDTO.setMessage(Result.SUCCESS.MSG);
                }else {
                    responseDTO.setCode(Result.PARAMERR.CODE);
                    responseDTO.setMessage(Result.PARAMERR.MSG);
                }
            }

        }else {
            responseDTO.setCode(Result.PARAMERR.CODE);
            responseDTO.setMessage(Result.PARAMERR.MSG);
        }

        responseDTO.setEmployee(employeeEntity);
        return responseDTO;
    }

    @Override
    public PageResponseDTO<memorandumEntity> memorandum_search(MemorandumRequestDTO requestDTO) throws Exception {
        PageResponseDTO<memorandumEntity> responseDTO=new PageResponseDTO<>();
        Pageable pageable=PageRequest.of(requestDTO.getPageNo()-1,requestDTO.getPageSize());
        Page list=memorandumEntityRepository.findAllItem(requestDTO.getAccountnumber(),requestDTO.getTitle()
                ,requestDTO.getContent(), requestDTO.getBelongDateStart(),requestDTO.getBelongDateEnd(),
                requestDTO.getUpdataTimeStart(),requestDTO.getUpdataTimeEnd(),pageable);
        if (list.getSize()!=0){
            responseDTO.setContent(list.getContent());
            responseDTO.setPageNo(requestDTO.getPageNo());
            responseDTO.setPageSize(requestDTO.getPageSize());
            responseDTO.setTotalPages(list.getTotalPages());
            responseDTO.setTotalElement(list.getNumberOfElements());
        }

        responseDTO.setCode(Result.SUCCESS.CODE);
        responseDTO.setMessage(Result.SUCCESS.MSG);
        return responseDTO;
    }

    @Override
    public ResultDTO memorandum_save(MemorandumRequestDTO requestDTO) throws Exception {
        ResultDTO result=new ResultDTO();
        memorandumEntity memorandumEntity = new memorandumEntity();
        //格式化时间今日00:00:00
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(requestDTO.getBelongDate());
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(dateString, pos);
        memorandumEntity memorandum=new memorandumEntity();
        memorandum=memorandumEntityRepository.findOneByAccountnumber(strtodate,requestDTO.getAccountnumber());

        if ((requestDTO.getTitle() == "") && (requestDTO.getContent() == "")){
            if (memorandum!=null){
                memorandumEntityRepository.delete(memorandum); ;
            }
        }else {
            if (memorandum!=null){
                memorandum.setTitle(requestDTO.getTitle());
                memorandum.setContent(requestDTO.getContent());
                memorandum.setUpdataTime(new Date());
                memorandumEntityRepository.save(memorandum);
            }else {
                memorandumEntity.setAccountnumber(requestDTO.getAccountnumber());
                memorandumEntity.setTitle(requestDTO.getTitle());
                memorandumEntity.setContent(requestDTO.getContent());
                memorandumEntity.setBelongDate(strtodate);
                memorandumEntity.setUpdataTime(new Date());
                memorandumEntityRepository.save(memorandumEntity);
            }
        }

        result.setCode(Result.SUCCESS.CODE);
        result.setMessage(Result.SUCCESS.MSG);
        return result;
    }



    @Override
    public ResultDTO sign_deal() {
        ResultDTO resultDTO=new ResultDTO();
        //昨日当前时间
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date time=cal.getTime();

        //格式化时间yyyy-MM-dd 00:00:00
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(time);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(dateString, pos);//昨日00：00：00

        //今日00：00：00
        Date today = new Date(strtodate.getTime()+24*60*60*1000L);

        //签到条目
        List<signDiaryEntity> sign_list=signDiaryEntityRepository.findByTime(strtodate,today);

        //账户条目
        Iterable<accountEntity> account_list=accountEntityRepository.findAll();
        Iterator it = account_list.iterator();

        ArrayList<Date> time_list;
        HashMap<String,ArrayList<Date>> hashMap = new HashMap<>();

        while (it.hasNext()) {
            accountEntity account = (accountEntity) it.next();
            time_list = new ArrayList<>();
            if (sign_list.size()!=0){
                for (signDiaryEntity signDiary : sign_list) {
                    if (account.getAccountnumber().equals(signDiary.getAccountnumber())) {
                        time_list.add(signDiary.getSignTime());
                        sign_list.remove(signDiary);
                        if (sign_list.size()==0){
                            break;
                        }
                    }
                }
            }else {
                time_list.add(strtodate);
            }
            time_list.sort(new Comparator<Date>() {
                @Override
                public int compare(Date o1, Date o2) {
                    if (o1 == null || o2 == null) return 0;

                    return o1.compareTo(o2);
                }
            });
            Date pre = time_list.get(0);
            Date last = time_list.get(time_list.size() - 1);

            //实际打卡时长
            Time work_time = new Time(last.getTime() - pre.getTime());

            signEntity signEntity = new signEntity();

            signEntity.setAccountnumber(account.getAccountnumber());
            signEntity.setSigninTime(pre);
            signEntity.setSignoutTime(last);

            //格林尼治时间1970-01-01 08:00:00,作为显示结果 +16小时(16*60*60*1000L)
            signEntity.setSignDuration(new Time(work_time.getTime()+16*60*60*1000L));
            //9小时工作时长
            if (work_time.getTime() < 9*60*60*1000L) {
                signEntity.setExtraduration(new Time(16*60*60*1000L));
                signEntity.setException("2");
                signEntity.setExceptionDetail("上班时长不够，还差" + (new Time(9*60*60*1000L - work_time.getTime()+16*60*60*1000L) ));
            } else if (work_time.getTime() == 9*60*60*1000L) {
                signEntity.setExtraduration(new Time(16*60*60*1000L));
                signEntity.setException("1");
                signEntity.setExceptionDetail("无异常");
            } else if (work_time.getTime() > 9*60*60*1000L) {
                signEntity.setExtraduration(new Time(work_time.getTime() - 9*60*60*1000L+16*60*60*1000L));
                signEntity.setException("1");
                signEntity.setExceptionDetail("无异常");
            }
            signEntityRepository.save(signEntity);

        }
        return resultDTO;
    }

    @Override
    public PageResponseDTO notice_get(notice_getRequestDTO requestDTO) throws Exception {
        PageResponseDTO responseDTO = new PageResponseDTO();
        Pageable pageable = PageRequest.of(requestDTO.getPageNo()-1,requestDTO.getPageSize());
        Page list=this.noticeEntityRepository.findByCondition(requestDTO.getId(),requestDTO.getCondition(),pageable);
        responseDTO.setContent(list.getContent());
        responseDTO.setPageNo(requestDTO.getPageNo());
        responseDTO.setPageSize(list.getSize());
        responseDTO.setTotalPages(list.getTotalPages());
        responseDTO.setTotalElement(list.getNumberOfElements());
        responseDTO.setCode(Result.SUCCESS.CODE);
        responseDTO.setMessage(Result.SUCCESS.MSG);
        return responseDTO;
    }

    @Override
    public ResultDTO notice_delete(notice_getRequestDTO requestDTO) throws Exception {
        ResultDTO resultDTO=new ResultDTO();
        noticeEntity noticeEntity=new noticeEntity();
        noticeEntity.setId(requestDTO.getId());
        try{
            this.noticeEntityRepository.delete(noticeEntity);
            resultDTO.setCode(Result.SUCCESS.CODE);
            resultDTO.setMessage(Result.SUCCESS.MSG);
        }catch (Exception e){
            resultDTO.setCode(Result.SYSERR.CODE);
            resultDTO.setMessage(Result.SYSERR.MSG);
        }
        return resultDTO;
    }

    @Override
    public ResultDTO notice_update(notice_getRequestDTO requestDTO) throws Exception {
        ResultDTO resultDTO= new ResultDTO();
        noticeEntity noticeEntity = new noticeEntity();
        noticeEntity=noticeEntityRepository.findOneBYid(requestDTO.getId());
        if(noticeEntity!=null){
            noticeEntity.setAccountnumber(requestDTO.getAccount());
            if (requestDTO.getName()!=null && requestDTO.getName()!=""){
                noticeEntity.setAccountnumber_name(requestDTO.getName());
            }
            if (requestDTO.getContent()!=null && requestDTO.getContent()!=""){
                noticeEntity.setMissionContent(requestDTO.getContent());
            }
            try{
                noticeEntityRepository.save(noticeEntity);
                resultDTO.setCode(Result.SUCCESS.CODE);
                resultDTO.setMessage(Result.SUCCESS.MSG);
            }catch (Exception e){
                resultDTO.setCode(Result.SYSERR.CODE);
                resultDTO.setMessage(Result.SYSERR.MSG);
            }
        }else {
            resultDTO.setCode(Result.SYSERR.CODE);
            resultDTO.setMessage(Result.SYSERR.MSG);
        }

        return resultDTO;
    }

    @Override
    public PageResponseDTO<employeeEntity> SearchEmployee(SearchEmployeeRequestDTO requestDTO) throws Exception {
        PageResponseDTO<employeeEntity> responseDTO=new PageResponseDTO<employeeEntity>();
        Pageable pageable=PageRequest.of(requestDTO.getPageNo()-1,requestDTO.getPageSize());
        Page pageList=employeeEntityRepository.getEmployee(requestDTO.getEmployeeId(),requestDTO.getCondition(),pageable);
        responseDTO.setPageNo(requestDTO.getPageNo());
        responseDTO.setPageSize(requestDTO.getPageSize());
        responseDTO.setTotalElement(pageList.getTotalElements());
        responseDTO.setTotalPages(pageList.getTotalPages());
        responseDTO.setContent(pageList.getContent());

        return responseDTO;
    }

    @Override
    public ResultDTO update_psw_manager(ManagerRequestDTO requestDTO) throws Exception {
        ResultDTO resultDTO=new ResultDTO();
        managerEntity managerEntity;
        managerEntity=this.managerEntityRepository.findByIdAndPSW(requestDTO.getAccountnumber(),requestDTO.getPassword());
        if (managerEntity!=null){
            managerEntity.setPassword(requestDTO.getPsw_new());
            managerEntityRepository.save(managerEntity);
            resultDTO.setCode(Result.SUCCESS.CODE);
            resultDTO.setMessage(Result.SUCCESS.MSG);
        }else {
            resultDTO.setCode(Result.SYSERR.CODE);
            resultDTO.setMessage("原密码错误");
        }
        return resultDTO;
    }

    @Override
    public ResultDTO update_psw(AccountRequestDTO requestDTO) throws Exception {
        ResultDTO resultDTO=new ResultDTO();
        accountEntity accountEntity;
        accountEntity=this.accountEntityRepository.findUser(requestDTO.getAccountnumber(),requestDTO.getPassword());
        if (accountEntity!=null){
            accountEntity.setPassword(requestDTO.getPsw_new());
            accountEntityRepository.save(accountEntity);
            resultDTO.setCode(Result.SUCCESS.CODE);
            resultDTO.setMessage(Result.SUCCESS.MSG);
        }else {
            resultDTO.setCode(Result.SYSERR.CODE);
            resultDTO.setMessage("原密码错误");
        }
        return resultDTO;
    }

    @Override
    public sign_dataResponseDTO sign_data() throws Exception {
        sign_dataResponseDTO responseDTO =new sign_dataResponseDTO();
        signEntity signEntity=new signEntity();
        Date date=new Date();
        int year=date.getYear();
        int month=date.getMonth();
        //上月人均总工时
        long workTime = 0L;
        //上月加班总时长
        long extraTime=0L;
        //上月补签数
        int si=0;
        //上月请假数
        int off=0;
        //上月一号
        Date lastMonth1;
        //本月一号
        Date lastMonth2= new Date(year,month,1);
        if (month==1){
            year-=1;
            month=12;
            lastMonth1= new Date(year,month,1);
        }else {
            lastMonth1= new Date(year,month-1,1);
        }
        //获取上月签到条目
        List<signEntity> list=this.signEntityRepository.findByTime(lastMonth1,lastMonth2);
        for (signEntity s:list) {
            workTime+=s.getSignDuration().getTime()+8*60*60*1000L;;
            extraTime+=s.getExtraduration().getTime()+8*60*60*1000L;
        }
        double workTimeRes=((double)workTime/list.size())/(60*60*1000L);
        double extraTimeRes=((double)extraTime)/(60*60*1000L);
        DecimalFormat df   = new DecimalFormat("######0.00");
        responseDTO.setAverageTime(String.valueOf(df.format(workTimeRes)));
        responseDTO.setWork_overtime(String.valueOf(df.format(extraTimeRes)));
        //获取上月申请条目
        List<offworkapplicationEntity> li=offWorkApplicationEntityRepository.findByTime(lastMonth1,lastMonth2);
        for (offworkapplicationEntity o:li) {
            if (o.getApplicationType()=="补签"){
                si+=1;
            }else {
                off+=1;
            }
        }
        responseDTO.setOffwork(off);
        responseDTO.setSignfix(si);
        return responseDTO;
    }

    @Override
    public sign_dataResponseDTO signStateLastSixMonths() throws Exception {
        sign_dataResponseDTO responseDTO = new sign_dataResponseDTO();
        signEntity signEntity=new signEntity();
        Date date=new Date();
        int year=date.getYear();
        int month=date.getMonth();
        //月异常数
        int exceptions=0;
        List<Integer> excList = new ArrayList<>();
        //月正常数
        int normals=0;
        List<Integer> norList = new ArrayList<>();
        //前一个月一号
        Date lastMonth1;
        //月一号
        Date lastMonth2;
        for (int i=0;i<6;i++){
            lastMonth2= new Date(year,month,1);
            month-=1;
            if (month==0){
                year-=1;
                month=12;
                lastMonth1= new Date(year,month,1);
            }else {
                lastMonth1= new Date(year,month-1,1);
            }
            //获取月签到条目
            List<signEntity> list=this.signEntityRepository.findByTime(lastMonth1,lastMonth2);
            for (signEntity s:list) {
                if (s.getException().equals("1")){
                    exceptions+=1;
                }
                if (s.getException().equals("2")){
                    normals+=1;
                }
            }
            excList.add(exceptions);
            norList.add(normals);
            exceptions=0;
            normals=0;

        }
        responseDTO.setExceptions(excList);
        responseDTO.setNormals(norList);
        return responseDTO;
    }

    @Override
    public ResultDTO notice_add(notice_getRequestDTO requestDTO) throws Exception {

        ResultDTO resultDTO=new ResultDTO();
        noticeEntity noticeEntity=new noticeEntity();
        noticeEntity.setAccountnumber(requestDTO.getAccount());
        noticeEntity.setAccountnumber_name(requestDTO.getName());
        noticeEntity.setCreateTime(requestDTO.getCreateTime());
        noticeEntity.setMissionContent(requestDTO.getContent());

        try{
            noticeEntityRepository.save(noticeEntity);
            resultDTO.setCode(Result.SUCCESS.CODE);
            resultDTO.setMessage(Result.SUCCESS.MSG);
        }catch (Exception e){
            resultDTO.setCode(Result.SYSERR.CODE);
            resultDTO.setMessage(Result.SYSERR.MSG);
        }

        return resultDTO;
    }

}
