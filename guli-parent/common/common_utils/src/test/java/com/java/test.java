package com.java;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.guli.utils.JWTUtils;
import org.junit.Test;

public class test {
    @Test
    public void testJWT(){
        String id = JWTUtils.getMemberIdByJwtToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJndWxpLXVzZXIiLCJpYXQiOjE2MDM2MDgwNTgsImV4cCI6MTYwMzY5NDQ1OCwiaWQiOiIxMzE5OTYwODg0Nzk3Mjk2NjQyIiwibmlja25hbWUiOiIzMzMzMyJ9.XvAKCb8ozdMq6qcALb30yEP8yOKaXZowMO9OIMy_BJY");
        System.out.println(id);

    }

}
