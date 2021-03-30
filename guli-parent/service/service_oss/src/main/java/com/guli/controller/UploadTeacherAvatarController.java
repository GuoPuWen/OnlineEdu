package com.guli.controller;

import com.guli.R;
import com.guli.services.UploadTeacherAvatarService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 四五又十
 * @version 1.0
 * @date 2020/10/4 1:55
 */
@RestController
@RequestMapping("/eduoss/fileUpload")
@CrossOrigin
public class UploadTeacherAvatarController {

    @Autowired
    private UploadTeacherAvatarService uploadTeacherAvatarService;


    @ApiOperation("上传教师头像文件")
    @PostMapping("/addTeacherAvatar")
    public R addTeacherAvatar(MultipartFile file) {
        String url = uploadTeacherAvatarService.upload(file);
        return R.ok().data("url", url).message("文件上传成功");
    }


}
