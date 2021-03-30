package com.guli.service_edu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guli.R;
import com.guli.handler.GuliException;
import com.guli.service_edu.entity.EduCourse;
import com.guli.service_edu.entity.vo.CourseInfoVo;
import com.guli.service_edu.entity.vo.CoursePublishVo;
import com.guli.service_edu.entity.vo.CourseQueryVo;
import com.guli.service_edu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author
 * @since 2020-10-07
 */
@RestController
@RequestMapping("/service_edu/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;


    @ApiOperation("保存课程信息")
    @PostMapping("/addCourse")
    public R addCourse(@RequestBody CourseInfoVo courseInfoVo) {
        String courseId = eduCourseService.addCourse(courseInfoVo);
        return R.ok().data("courseId", courseId);
    }

    @ApiOperation("根据id查询课程信息")
    @GetMapping("/findById/{courseId}")
    public R findById(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = eduCourseService.findById(courseId);
        return R.ok().data("courseInfo", courseInfoVo);
    }

    @ApiOperation("根据id修改课程信息")
    @PostMapping("/updateById")
    public R updateById(@RequestBody CourseInfoVo courseInfoVo) {
        int update = eduCourseService.updatebyId(courseInfoVo);
        if (update == 0) {
            throw new GuliException(20001, "修改失败");
        }
        return R.ok();
    }

    @ApiOperation("根据CourseId查询最后的课程发布信息")
    @GetMapping("/findCoursePublishById/{courseId}")
    public R findCoursePublishById(@PathVariable String courseId) {
        CoursePublishVo coursePublish = eduCourseService.findCoursePublishById(courseId);
        return R.ok().data("coursePublish", coursePublish);
    }

    @ApiOperation("根据id发布课程")
    @PutMapping("/publishCourse/{courseId}")
    public R publishCourse(@PathVariable String courseId) {
        return eduCourseService.publishCourse(courseId) ? R.ok() : R.error();
    }

    @ApiOperation("课程展示页面，组合条件查询并且分页")
    @PostMapping("/courseQuery/{current}/{size}")
    public R pageCourseQuery(@PathVariable Integer current, @PathVariable Integer size, @RequestBody(required = false) CourseQueryVo courseQuery) {
        IPage<EduCourse> pageCourseInfo = eduCourseService.courseQuery(courseQuery, current, size);
        long total = pageCourseInfo.getTotal();
        List<EduCourse> records = pageCourseInfo.getRecords();
        return R.ok().data("total", total).data("pageCourseInfo", records);
    }

    //删除课程的同时，会删除该课程下的所有章节，小节，以及视频
    @ApiOperation("删除课程")
    @DeleteMapping("/deleteCourse/{courseId}")
    public R deleteCourse(@PathVariable String courseId) {
        return eduCourseService.deleteCourse(courseId) ? R.ok() : R.error();
    }


}

