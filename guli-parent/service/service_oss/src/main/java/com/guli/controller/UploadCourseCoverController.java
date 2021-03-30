package com.guli.controller;

import com.guli.R;
import com.guli.services.UploadCourseCoverService;
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
public class UploadCourseCoverController {

    @Autowired
    private UploadCourseCoverService uploadCourseCoverService;


    @ApiOperation("上传课程封面文件")
    @PostMapping("/addCourseCover")
    public R addTeacherAvatar(MultipartFile file) {
        String url = uploadCourseCoverService.upload(file);
        return R.ok().data("url", url).message("文件上传成功");
    }


}
