package com.guli.service_edu.controller.front;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guli.R;
import com.guli.service_edu.entity.EduCourse;
import com.guli.service_edu.entity.EduTeacher;
import com.guli.service_edu.entity.vo.FrontCourseQueryVo;
import com.guli.service_edu.entity.vo.FrontCourseVo;
import com.guli.service_edu.service.EduCourseService;
import com.sun.tracing.dtrace.ProviderAttributes;
import io.swagger.annotations.ApiOperation;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/front/service_edu/course")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService eduCourseService;


    @ApiOperation("前台页面查询课程")
    @GetMapping("/getCourseList")
    public R getCourseList() {
        List<EduCourse> courseList = eduCourseService.getCourseList();
        return R.ok().data("courseList", courseList);
    }

    @ApiOperation("课程页面数据显示")
    @PostMapping("/pageCourseListQuery/{current}/{size}")
    public R pageCourseListQuery(@PathVariable Integer current, @PathVariable Integer size, @RequestBody(required = false) FrontCourseQueryVo courseQueryVo) {
        IPage<EduCourse> pageCourse = eduCourseService.pageCourseListQuery(current, size, courseQueryVo);
        long total = pageCourse.getTotal();
        List<EduCourse> courseList = pageCourse.getRecords();
        return R.ok().data("courseList", courseList).data("total", total);
    }

    @ApiOperation("课程详情页面，也就是根据id查询课程")
    @GetMapping("/findCourseInfoById/{id}")
    public R findCourseInfoById(@PathVariable String id) {
        FrontCourseVo course = eduCourseService.findCourseInfoById(id);
        return R.ok().data("course", course);
    }


}
