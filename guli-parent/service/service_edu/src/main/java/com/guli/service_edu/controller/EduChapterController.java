package com.guli.service_edu.controller;


import com.guli.R;
import com.guli.service_edu.entity.EduChapter;
import com.guli.service_edu.entity.vo.ChapterVo;
import com.guli.service_edu.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/service_edu/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation("查询所有章节以及对于的小节")
    @GetMapping("/findAll/{courseId}")
    public R findAll(@PathVariable String courseId) {
        List<ChapterVo> ChapterVideoLists = eduChapterService.findAll(courseId);
        return R.ok().data("chapter", ChapterVideoLists);
    }

    @ApiOperation("添加课程章节")
    @PostMapping("/save/{courseId}")
    @Transactional
    public R save(@PathVariable String courseId, @RequestBody EduChapter chapter) {
        return eduChapterService.save(chapter) ? R.ok() : R.error();
    }

    @ApiOperation("根据章节id查询章节，进行信息回显")
    @GetMapping("/findChapterById/{chapterId}")
    public R findChapterById(@PathVariable String chapterId) {
        EduChapter chapter = eduChapterService.getById(chapterId);
        return R.ok().data("chapter", chapter);
    }

    @ApiOperation("修改课程章节")
    @PostMapping("/update")
    @Transactional
    public R update(@RequestBody EduChapter chapter) {
        System.out.println(chapter);
        return eduChapterService.updateById(chapter) ? R.ok() : R.error();
    }

    @ApiOperation("删除课程章节")
    @DeleteMapping("/deleteChapter/{chapterId}")
    @Transactional
    public R deleteChapter(@PathVariable String chapterId) {
        return eduChapterService.deleteChapter(chapterId) ? R.ok() : R.error();
    }


}

