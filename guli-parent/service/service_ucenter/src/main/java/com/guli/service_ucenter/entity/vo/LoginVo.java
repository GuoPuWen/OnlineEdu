package com.guli.service_ucenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginVo {
    @ApiModelProperty(value = "手机号")
    private String phoneNum;

    @ApiModelProperty(value = "密码")
    private String password;
}
