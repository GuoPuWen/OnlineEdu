package com.guli.service_cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.service_cms.entity.CmsBanner;
import com.guli.service_cms.entity.vo.BannerQueryVo;
import com.guli.service_cms.mapper.CmsBannerMapper;
import com.guli.service_cms.service.CmsBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author
 * @since 2020-10-17
 */
@Service
public class CmsBannerServiceImpl extends ServiceImpl<CmsBannerMapper, CmsBanner> implements CmsBannerService {


    @Override
    public IPage<CmsBanner> pageList(Integer current, Integer size, BannerQueryVo bannerQueryVo) {
        QueryWrapper<CmsBanner> cmsBannerQueryWrapper = new QueryWrapper<>();
        String title = bannerQueryVo.getTitle();
        Integer flag = bannerQueryVo.getFlag();
        String begin = bannerQueryVo.getBegin();
        String end = bannerQueryVo.getEnd();
        if (!StringUtils.isEmpty(title)) {
            cmsBannerQueryWrapper.eq("title", title);
        }
        if (!StringUtils.isEmpty(flag)) {
            cmsBannerQueryWrapper.eq("flag", flag);
        }
        if (!StringUtils.isEmpty(begin)) {
            cmsBannerQueryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            cmsBannerQueryWrapper.le("gmt_modified", end);
        }
        Page<CmsBanner> cmsBannerPage = new Page<>(current, size);
        IPage<CmsBanner> page = this.page(cmsBannerPage, cmsBannerQueryWrapper);

        return page;
    }


    @Cacheable(value = "banner", key = "'selectIndexList'")
    @Override
    public List<CmsBanner> getBanner() {
        QueryWrapper<CmsBanner> cmsBannerQueryWrapper = new QueryWrapper<>();
        cmsBannerQueryWrapper.eq("flag", 1);
        List<CmsBanner> list = this.list(cmsBannerQueryWrapper);
        return list;
    }


}
