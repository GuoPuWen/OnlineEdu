package com.guli.service_ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.handler.GuliException;
import com.guli.service_ucenter.entity.vo.RegisterVo;
import com.guli.utils.JWTUtils;
import com.guli.utils.MD5;
import com.guli.service_ucenter.entity.UcenterMember;
import com.guli.service_ucenter.entity.vo.LoginVo;
import com.guli.service_ucenter.mapper.UcenterMemberMapper;
import com.guli.service_ucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.netflix.hystrix.metric.sample.HystrixUtilization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author
 * @since 2020-10-23
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(LoginVo loginVo) {
        String phoneNum = loginVo.getPhoneNum();
        String password = loginVo.getPassword();
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(phoneNum)) {
            throw new GuliException(20001, "手机号或者密码为空");
        }
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", phoneNum);
        UcenterMember member = baseMapper.selectOne(wrapper);

        if (member == null) {
            throw new GuliException(20001, "用户不存在。请注册");
        }

        if (!MD5.encrypt(password).equals(member.getPassword())) {
            throw new GuliException(20001, "密码错误");
        }

        if (member.getIsDisabled()) {
            throw new GuliException(20001, "用户被禁止登录，请联系管理员");
        }
        String token = JWTUtils.getJwtToken(member.getId(), member.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        String code = registerVo.getCode();
        String phoneNum = registerVo.getPhoneNum();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        //验证参数
        if (StringUtils.isEmpty(phoneNum)) {
            throw new GuliException(20001, "电话为空!");
        }
        if (StringUtils.isEmpty(nickname)) {
            throw new GuliException(20001, "昵称为空!");
        }
        if (StringUtils.isEmpty(code)) {
            throw new GuliException(20001, "验证码为空!");
        }
        if (StringUtils.isEmpty(password)) {
            throw new GuliException(20001, "密码为空!");
        }
        //验证手机号是否为空
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", phoneNum);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new GuliException(20001, "手机号已经注册，请登录！");
        }

        //验证验证码是否正确
        String mobileCode = redisTemplate.opsForValue().get(phoneNum);
        if (!code.equals(mobileCode)) {
            throw new GuliException(20001, "验证码错误");
        }
        UcenterMember member = new UcenterMember();
        member.setNickname(nickname);
        member.setMobile(registerVo.getPhoneNum());
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        this.save(member);
    }
}
