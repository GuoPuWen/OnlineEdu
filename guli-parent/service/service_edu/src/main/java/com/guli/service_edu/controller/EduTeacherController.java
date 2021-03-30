package com.guli.service_edu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.R;
import com.guli.service_edu.entity.EduTeacher;
import com.guli.service_edu.entity.vo.TeacherQueryVo;
import com.guli.service_edu.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author
 * @since 2020-09-28
 */
@RestController
@RequestMapping("/service_edu/teacher")
@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation("查询所有讲师")
    @GetMapping("/findAll")
    public R list() {
        R r = R.ok();
        List<EduTeacher> teachers = eduTeacherService.list(null);
        return r.data("item", teachers);
    }

    @ApiOperation("根据id删除讲师")
    @DeleteMapping("delete/{id}")
    public R removeById(@PathVariable String id) {

        return eduTeacherService.removeById(id) ? R.ok() : R.error();
    }

    @ApiOperation("查询结果并且分页")
    @GetMapping("/pageTeacherList/{current}/{size}")
    public R pageTeachertList(@PathVariable Integer current, @PathVariable Integer size) {

        Page<EduTeacher> eduTeacherPage = new Page<>(current, size);
        eduTeacherService.page(eduTeacherPage, null);
        long total = eduTeacherPage.getTotal();
        List<EduTeacher> teachers = eduTeacherPage.getRecords();
        return R.ok().data("total", total).data("rows", teachers);

    }

    @ApiOperation("组合条件查询并且分页")
    @PostMapping("/pageTeacherQuery/{current}/{size}")
    public R pageTeacherQuery(@PathVariable Integer current, @PathVariable Integer size, @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        //System.out.println(teacherQueryVo);
        IPage<EduTeacher> eduTeacherIPage = eduTeacherService.pageTeacherQuery(current, size, teacherQueryVo);
        long total = eduTeacherIPage.getTotal();
        List<EduTeacher> teachers = eduTeacherIPage.getRecords();
        return R.ok().data("total", total).data("rows", teachers);
    }

    @ApiOperation("新增讲师")
    @PostMapping("/save")
    public R save(@RequestBody EduTeacher eduTeacher) {
        return eduTeacherService.save(eduTeacher) ? R.ok() : R.error();
    }

    @ApiOperation("根据ID修改讲师")
    @PutMapping("/updateById/{id}")
    public R updateById(@PathVariable String id, @RequestBody EduTeacher eduTeacher) {
        eduTeacher.setId(id);
        return eduTeacherService.updateById(eduTeacher) ? R.ok() : R.error();
    }

    @ApiOperation("根据id查询")
    @GetMapping("/findById/{id}")
    public R findById(@PathVariable String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("teacher", teacher);
    }

}

