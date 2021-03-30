package com.guli.service_ucenter.service;

import com.guli.service_ucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.service_ucenter.entity.vo.LoginVo;
import com.guli.service_ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author
 * @since 2020-10-23
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);
}
