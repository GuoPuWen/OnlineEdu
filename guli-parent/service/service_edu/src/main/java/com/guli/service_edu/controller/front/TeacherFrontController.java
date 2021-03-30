package com.guli.service_edu.controller.front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.R;
import com.guli.service_edu.entity.EduCourse;
import com.guli.service_edu.entity.EduTeacher;
import com.guli.service_edu.service.EduCourseService;
import com.guli.service_edu.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Queue;

@RestController
@RequestMapping("/front/service_edu/teacher")
@CrossOrigin
public class TeacherFrontController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation("前台首页页面获取讲师列表")
    @GetMapping("/getTeacherList")
    public R getTeacherList() {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.eq("flag", 1);
        List<EduTeacher> list = eduTeacherService.list(wrapper);
        return R.ok().data("teachers", list);
    }

    @ApiOperation("讲师列表获取所有的讲师")
    @GetMapping("/getAllTeacher/{current}/{size}")
    public R getAllteacher(@PathVariable Integer current, @PathVariable Integer size) {

        Page<EduTeacher> eduTeacherPage = new Page<>(current, size);
        eduTeacherService.page(eduTeacherPage, null);
        long total = eduTeacherPage.getTotal();
        List<EduTeacher> teachers = eduTeacherPage.getRecords();
        boolean hasNext = eduTeacherPage.hasNext();
        boolean hasPrevious = eduTeacherPage.hasPrevious();
        long current1 = eduTeacherPage.getCurrent();
        return R.ok().data("total", total).data("rows", teachers).data("hasNext", hasNext).data("hasPrevious", hasPrevious);
    }

    @ApiOperation("根据id查询出讲师，用于做讲师详情")
    @GetMapping("/getTeacherById/{id}")
    public R getTeacherById(@PathVariable String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", id);
        List<EduCourse> courses = eduCourseService.list(wrapper);
        return R.ok().data("teacher", teacher).data("courses", courses);
    }


}
