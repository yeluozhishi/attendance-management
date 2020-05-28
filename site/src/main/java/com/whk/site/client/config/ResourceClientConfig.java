package com.whk.site.client.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.whk.site.util.InputStreamUtil;
import feign.Contract;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Configuration
public class ResourceClientConfig implements ErrorDecoder {

    /**
     * LOGGER
     */
    private static final Logger LOG = LoggerFactory.getLogger(ResourceClientConfig.class);

    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    @Override
    public Exception decode(String s, Response response) {
        Exception exception = null;

        if (400 <= response.status() || response.status() < 500) {
            String msg = null;
            try {
                String responseStr =
                        InputStreamUtil.InputStreamTOString(response.body().asInputStream(), "UTF-8");
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(responseStr);
                msg = root.get("message").textValue();
            } catch (IOException ex) {
                msg = "invalid request!";
            } catch (Exception e) {
                e.printStackTrace();
            }
            LOG.debug(msg);
            exception =                                                                                                                                                                                                                                                                                                                                                                                                                                                                  new HystrixBadRequestException(msg);
            return exception;
        } else {
            return new ErrorDecoder.Default().decode(s, response);
        }
    }

    /**
     * feign Jackson传输Date时区误差
     * 设置时区为亚洲
     * @return
     */
    private static final ObjectMapper mapper=new ObjectMapper();
    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    }
}
