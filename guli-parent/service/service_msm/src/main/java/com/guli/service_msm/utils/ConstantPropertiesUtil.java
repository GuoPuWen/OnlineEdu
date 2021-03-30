package com.guli.service_msm.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 四五又十
 * @version 1.0
 * @date 2020/10/12 16:45
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {
    @Value("${aliyun.msm.keyid}")
    private String keyId;

    @Value("${aliyun.msm.keysecret}")
    private String keySecret;

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
    }
}
