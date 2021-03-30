package com.guli.service_cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guli.service_cms.entity.CmsBanner;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.service_cms.entity.vo.BannerQueryVo;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author
 * @since 2020-10-17
 */
public interface CmsBannerService extends IService<CmsBanner> {

    IPage<CmsBanner> pageList(Integer current, Integer size, BannerQueryVo bannerQueryVo);

    List<CmsBanner> getBanner();
}
