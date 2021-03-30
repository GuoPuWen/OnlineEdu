package com.guli.service_ucenter.controller;


import com.guli.R;
import com.guli.service_ucenter.entity.UcenterMember;
import com.guli.service_ucenter.entity.vo.LoginVo;
import com.guli.service_ucenter.entity.vo.RegisterVo;
import com.guli.service_ucenter.service.UcenterMemberService;
import com.guli.utils.JWTUtils;
import io.jsonwebtoken.Jwt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author
 * @since 2020-10-23
 */
@RestController
@RequestMapping("/service_ucenter/ucenter")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public R login(@RequestBody LoginVo user) {
        String token = ucenterMemberService.login(user);
        return R.ok().data("token", token);
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public R register(@RequestBody RegisterVo registerVo) {
        ucenterMemberService.register(registerVo);
        return R.ok();
    }

    @ApiOperation("根据token获取用户信息")
    @GetMapping("/auth/getLoginInfo")
    public R getLoginInfo(HttpServletRequest request) {
        String memberId = JWTUtils.getMemberIdByJwtToken(request);
        System.out.println(memberId);
        UcenterMember member = ucenterMemberService.getById(memberId);
        return R.ok().data("member", member);
    }

}

