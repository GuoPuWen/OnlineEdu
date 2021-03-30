package com.guli.controller;

import com.guli.R;
import com.guli.services.UploadBannerService;
import com.guli.services.UploadTeacherAvatarService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 四五又十
 * @version 1.0
 * @date 2020/10/4 1:55
 */
@RestController
@RequestMapping("/eduoss/fileUpload")
@CrossOrigin
public class UploadBannerController {

    @Autowired
    private UploadBannerService UploadBannerService;


    @ApiOperation("上传banner图片文件")
    @PostMapping("/addBanner")
    public R addTeacherAvatar(MultipartFile file) {
        String url = UploadBannerService.upload(file);
        System.out.println(url);
        return R.ok().data("url", url).message("文件上传成功");
    }


}
