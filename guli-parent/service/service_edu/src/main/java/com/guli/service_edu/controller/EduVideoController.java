package com.guli.service_edu.controller;


import com.guli.R;
import com.guli.service_edu.entity.EduChapter;
import com.guli.service_edu.entity.EduVideo;
import com.guli.service_edu.service.EduVideoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author
 * @since 2020-10-07
 */
@RestController
@RequestMapping("/service_edu/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @ApiOperation("添加小节")
    @PostMapping("/addVideo/{courseId}/{chapterId}")
    public R addVideo(@PathVariable String courseId, @PathVariable String chapterId, @RequestBody EduVideo video) {
        video.setChapterId(chapterId);
        video.setCourseId(courseId);
        return eduVideoService.save(video) ? R.ok() : R.error();
    }

    @ApiOperation("根据vedioId来删除小节")
    @DeleteMapping("/delete/{videoId}")
    public R delete(@PathVariable String videoId) {

        return eduVideoService.deleteVideoByVideoId(videoId) ? R.ok() : R.error();
    }

    @ApiOperation("根据id查询章节，用于信息回显")
    @GetMapping("/findVideoById/{chapterId}")
    public R findVideoById(@PathVariable String chapterId) {
        EduVideo video = eduVideoService.getById(chapterId);
        return R.ok().data("video", video);
    }

    @ApiOperation("修改小节")
    @PostMapping("/update/{chapterId}")
    public R update(@PathVariable String chapterId, @RequestBody EduVideo video) {
        video.setId(chapterId);
        return eduVideoService.updateById(video) ? R.ok() : R.error();
    }


}

