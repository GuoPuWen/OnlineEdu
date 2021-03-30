package com.guli.service_cms.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.R;
import com.guli.service_cms.entity.CmsBanner;
import com.guli.service_cms.entity.vo.BannerQueryVo;
import com.guli.service_cms.service.CmsBannerService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.awt.color.ICC_ColorSpace;
import java.util.List;

/**
 * @author 四五又十
 * @version 1.0
 * @date 2020/10/17 18:45
 */
@RestController
@RequestMapping("/service_cms/admin/banner")
@CrossOrigin
public class CmsBannerController {
    @Autowired
    private CmsBannerService cmsBannerService;

    @ApiOperation("新增Banner")
    @PostMapping("/addBanner")
    public R addBanner(@RequestBody CmsBanner banner) {
        System.out.println(banner);
        return cmsBannerService.save(banner) ? R.ok() : R.error();
    }

    @ApiOperation("获取Banner分页列表")
    @GetMapping("/list/{current}/{size}")
    public R list(@PathVariable Integer current, @PathVariable Integer size) {
        Page<CmsBanner> page = new Page<CmsBanner>(current, size);
        IPage<CmsBanner> bannerIPage = cmsBannerService.page(page, null);
        long total = bannerIPage.getTotal();
        List<CmsBanner> records = bannerIPage.getRecords();
        return R.ok().data("total", total).data("lists", records);
    }

    @ApiOperation("获取banner并且条件查询")
    @PostMapping("/pageList/{current}/{size}")
    public R pageList(@PathVariable Integer current, @PathVariable Integer size, @RequestBody BannerQueryVo bannerQueryVo) {
        Page<CmsBanner> page = new Page<CmsBanner>(current, size);
        IPage<CmsBanner> bannerIPage = cmsBannerService.pageList(current, size, bannerQueryVo);
        List<CmsBanner> records = bannerIPage.getRecords();
        long total = bannerIPage.getTotal();
        //System.out.println(total);
        return R.ok().data("total", total).data("lists", records);
    }

    @ApiOperation("删除banner")
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable String id) {
        return cmsBannerService.removeById(id) ? R.ok() : R.error();
    }

    @ApiOperation("修改banner")
    @PostMapping("/update")
    public R update(@RequestBody CmsBanner banner) {
        return cmsBannerService.updateById(banner) ? R.ok() : R.error();
    }

    @ApiOperation("通过id查询banner")
    @GetMapping("/findById/{id}")
    public R findById(@PathVariable String id) {
        CmsBanner banner = cmsBannerService.getById(id);
        return R.ok().data("banner", banner);
    }


}
