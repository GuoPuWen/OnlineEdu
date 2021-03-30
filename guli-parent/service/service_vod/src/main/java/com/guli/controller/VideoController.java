package com.guli.controller;

import com.guli.R;
import com.guli.service.VideoService;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 四五又十
 * @version 1.0
 * @date 2020/10/12 21:51
 */
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping("/upload")
    public R upload(MultipartFile file) {
        String videoId = videoService.uploadVideo(file);
        return R.ok().data("videoId", videoId);
    }

    @DeleteMapping("/delete/{id}")
    public R delete(
            @ApiParam(name = "id", value = "云端视频id", required = true)
            @PathVariable String id) {
        videoService.removeVideo(id);
        return R.ok().message("删除成功！");
    }


}
