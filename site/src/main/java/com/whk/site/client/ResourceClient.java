package com.whk.site.client;


import com.alibaba.fastjson.JSONObject;
import com.whk.site.client.config.ResourceClientConfig;
import com.whk.site.domain.dto.*;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import static com.whk.site.constant.ResourceConstant.*;

@FeignClient(
        name = "resource",
        url = "http://localhost:8082",
        configuration = ResourceClientConfig.class,
        fallback = ResourceClient.ResourceClientFallback.class
)

public interface ResourceClient {

    //登录
    @RequestLine("POST "+GET_USERLOGIN_PATH)
    JSONObject login(@RequestBody LoginUserRequestDTO requestDTO) throws Exception;

    //打卡
    @RequestLine("POST "+GET_SIGN_PATH)
    JSONObject sign(@RequestBody SignDiaryRequestDTO requestDTO ) throws Exception;

    //查找职员信息
    @RequestLine("POST "+GET_SEARCHEMPLOYEEINFO_PATH)
    JSONObject GetUserInfo(AccountRequestDTO accountRequestDTO) throws Exception;

    //保存备忘录
    @RequestLine("POST "+GET_MEMORANDUM_SAVE_PATH)
    JSONObject memorandum_save (MemorandumRequestDTO request) throws Exception;

    //查找备忘录
    @RequestLine("POST "+GET_MEMORANDUM_SEARCH_PATH)
    JSONObject memorandum_search(MemorandumRequestDTO requestDTO) throws Exception;

    //获取签到条目
    @RequestLine("POST "+GET_SEARCHSIGNITEM_PATH)
    JSONObject SearchSignItem(SignRequestDTO requestDTO) throws Exception;

    //获取请假条目
    @RequestLine("POST "+GET_SEARCHOFFWORKAPPLICATION_PATH)
    JSONObject GetOffworkApplicationItem(SearchOffWorkApplicationRequestDTO requestDTO) throws Exception;

    //获取请假条目
    @RequestLine("POST "+GET_OFFWORKAPPLICATION_PATH)
    JSONObject OffworkApplicationItem(OffWorkApplicationRequestDTO requestDTO) throws Exception;

    //获取任务
    @RequestLine("POST "+GET_NOTICE_GET_PATH)
    JSONObject notice_get(Mission_getRequestDTO requestDTO) throws Exception;

    //获取签到状态
    @RequestLine("POST "+GET_SIGNSTATE_PATH)
    JSONObject signState(SignDiaryRequestDTO requestDTO) throws Exception;

    //修改密码
    @RequestLine("POST "+GET_UPDATE_PSW_PATH)
    JSONObject update_psw(AccountRequestDTO requestDTO) throws Exception;


    @Component
    public class ResourceClientFallback implements ResourceClient {
        /**
         *
         * @param requestDTO
         * @return
         * @throws Exception
         */
        @Override
        public JSONObject login(LoginUserRequestDTO requestDTO) throws Exception {
            JSONObject error=new JSONObject();
            error.put("error","404");
            return error;
        }

        /**
         *
         * @param requestDTO
         * @return
         * @throws Exception
         */
        @Override
        public JSONObject sign(SignDiaryRequestDTO requestDTO) throws Exception {
            JSONObject error=new JSONObject();
            error.put("error","404");
            return error;
        }

        /**
         *
         * @param accountRequestDTO
         * @return
         * @throws Exception
         */
        @Override
        public JSONObject GetUserInfo(AccountRequestDTO accountRequestDTO) throws Exception {
            JSONObject error=new JSONObject();
            error.put("error","404");
            return error;
        }

        /**
         *
         * @param request
         * @return
         * @throws Exception
         */
        @Override
        public JSONObject memorandum_save(MemorandumRequestDTO request) throws Exception {
            JSONObject error=new JSONObject();
            error.put("error","404");
            return error;
        }

        /**
         *
         * @param requestDTO
         * @return
         * @throws Exception
         */
        @Override
        public JSONObject memorandum_search(MemorandumRequestDTO requestDTO) throws Exception {
            JSONObject error=new JSONObject();
            error.put("error","404");
            return error;
        }

        /**
         *
         * @param requestDTO
         * @return
         * @throws Exception
         */
        @Override
        public JSONObject SearchSignItem(SignRequestDTO requestDTO) throws Exception {
            JSONObject error=new JSONObject();
            error.put("error","404");
            return error;
        }

        /**
         *
         * @param requestDTO
         * @return
         * @throws Exception
         */
        @Override
        public JSONObject GetOffworkApplicationItem(SearchOffWorkApplicationRequestDTO requestDTO) throws Exception {
            JSONObject error=new JSONObject();
            error.put("error","404");
            return error;
        }

        /**
         *
         * @param requestDTO
         * @return
         * @throws Exception
         */
        @Override
        public JSONObject OffworkApplicationItem(OffWorkApplicationRequestDTO requestDTO) throws Exception {
            JSONObject error=new JSONObject();
            error.put("error","404");
            return error;
        }

        /**
         *
         * @param requestDTO
         * @return
         * @throws Exception
         */
        @Override
        public JSONObject notice_get(Mission_getRequestDTO requestDTO) throws Exception {
            JSONObject error=new JSONObject();
            error.put("error","404");
            return error;
        }

        /**
         *
         * @param requestDTO
         * @return
         * @throws Exception
         */
        @Override
        public JSONObject signState(SignDiaryRequestDTO requestDTO) throws Exception {
            JSONObject error=new JSONObject();
            error.put("error","404");
            return error;
        }

        /**
         *
         * @param requestDTO
         * @return
         * @throws Exception
         */
        @Override
        public JSONObject update_psw(AccountRequestDTO requestDTO) throws Exception {
            JSONObject error=new JSONObject();
            error.put("error","404");
            return error;
        }
    }
}

