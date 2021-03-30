package com.guli.service_cms.controller;

import com.guli.R;
import com.guli.service_cms.entity.CmsBanner;
import com.guli.service_cms.service.CmsBannerService;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.valves.RemoteIpValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 四五又十
 * @version 1.0
 * @date 2020/10/17 18:44
 */
@RestController
@RequestMapping("/service_cms/front/banner")
@CrossOrigin
public class BannerApiController {

    @Autowired
    private CmsBannerService cmsBannerService;

    @ApiOperation("首页显示banner")
    @GetMapping("/getBanner")
    public R getBanner() {

        List<CmsBanner> banner = cmsBannerService.getBanner();
        return R.ok().data("banner", banner);
    }
}
