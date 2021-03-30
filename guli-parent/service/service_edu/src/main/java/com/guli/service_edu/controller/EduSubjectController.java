package com.guli.service_edu.controller;


import com.guli.R;
import com.guli.service_edu.entity.vo.OneSubject;
import com.guli.service_edu.service.EduSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author
 * @since 2020-10-05
 */

@RestController
@RequestMapping("/service_edu/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("/upload")
    @ApiOperation(value = "Excel批量导入")
    public R upload(MultipartFile file) {
        eduSubjectService.upload(file, eduSubjectService);
        return R.ok().message("上传成功");
    }

    @GetMapping("/findAll")
    @ApiOperation("查询所有课程列表")
    public R list() {
        List<OneSubject> list = eduSubjectService.findAll();
        return R.ok().data("list", list);
    }

}

