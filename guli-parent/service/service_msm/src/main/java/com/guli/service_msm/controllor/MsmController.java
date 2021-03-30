package com.guli.service_msm.controllor;

import com.guli.R;
import com.guli.handler.GuliException;
import com.guli.service_msm.service.MsmService;
import com.guli.service_msm.utils.RandomUtil;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/service_msm/msm")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/send/{phone}")
    public R send(@PathVariable String phone) {
        //从redis中获取code，如果redis中没有在发送验证码
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            throw new GuliException(20001, "不可重复操作，请5分钟后再试");
        }
        code = RandomUtil.getFourBitRandom();

        Map<String, String> param = new HashMap<>();
        param.put("code", code);
        boolean isSend = msmService.send(phone, param);
        if (isSend) {
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
        }
        return isSend ? R.ok() : R.error().message("发送失败");
    }
}
