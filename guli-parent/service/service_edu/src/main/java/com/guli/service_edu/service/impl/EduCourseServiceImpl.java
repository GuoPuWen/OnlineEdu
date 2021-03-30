package com.guli.service_edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.handler.GuliException;
import com.guli.service_edu.entity.EduCourse;
import com.guli.service_edu.entity.EduCourseDescription;
import com.guli.service_edu.entity.EduTeacher;
import com.guli.service_edu.entity.vo.*;
import com.guli.service_edu.mapper.EduCourseMapper;
import com.guli.service_edu.service.EduChapterService;
import com.guli.service_edu.service.EduCourseDescriptionService;
import com.guli.service_edu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.service_edu.service.EduVideoService;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author
 * @since 2020-10-07
 */
@Service

public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService eduChapterService;


    @Override
    public String addCourse(CourseInfoVo courseInfoVo) {
        //添加课程表
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, course);
        if (courseInfoVo.getDescription().getBytes().length > 65535) {
            throw new GuliException(2001, "课程简介图片过大或者文字过长!");
        }
        this.save(course);
        //添加课程简介表,这里不能让简介表自动生成id
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(course.getId());
        eduCourseDescriptionService.save(courseDescription);
        return course.getId();
    }

    @Override
    public CourseInfoVo findById(String courseId) {
        //获取课程简介信息
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);
        //获取课程信息
        EduCourse eduCourse = this.getById(courseId);
        CourseInfoVo courseInfo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfo);
        courseInfo.setDescription(courseDescription.getDescription());
        return courseInfo;
    }

    @Override
    public int updatebyId(CourseInfoVo courseInfoVo) {
        EduCourse course = new EduCourse();
        EduCourseDescription courseDescription = new EduCourseDescription();

        BeanUtils.copyProperties(courseInfoVo, course);

        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(courseInfoVo.getId());

        boolean b = this.updateById(course);
        boolean b1 = eduCourseDescriptionService.updateById(courseDescription);
        if (b && b1) {
            return 1;
        } else {
            return 0;
        }


    }

    @Override
    public CoursePublishVo findCoursePublishById(String courseId) {
        return baseMapper.findCoursePublishById(courseId);
    }

    @Override
    public boolean publishCourse(String courseId) {
        EduCourse course = new EduCourse();
        course.setId(courseId);
        course.setStatus(EduCourse.COURSE_NORMAL);
        return baseMapper.updateById(course) > 0;
    }

    @Override
    public IPage<EduCourse> courseQuery(CourseQueryVo courseQuery, Integer current, Integer size) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        IPage<EduCourse> pageCourseInfo = new Page<>(current, size);
        if (courseQuery == null) {
            return this.page(pageCourseInfo, null);
        }
        String title = courseQuery.getTitle();
        String subjectId = courseQuery.getSubjectId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String teacherId = courseQuery.getTeacherId();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }

        if (!StringUtils.isEmpty(teacherId)) {
            queryWrapper.eq("teacher_id", teacherId);
        }

        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.ge("subject_parent_id", subjectParentId);
        }

        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.ge("subject_id", subjectId);
        }

        this.page(pageCourseInfo, queryWrapper);
        return pageCourseInfo;
    }

    @Override
    public boolean deleteCourse(String courseId) {
        //首先删除课程下所有小节


        if (eduVideoService.deleteVideoByCourseId(courseId) &&
                eduChapterService.deleteChapterByCourseId(courseId) &&
                this.removeById(courseId)) {
            return true;
        }
        return false;
    }

    @Override
    public List<EduCourse> getCourseList() {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("status", "Normal");
        List<EduCourse> courseList = this.list(wrapper);
        return courseList;
    }

    @Override
    public IPage<EduCourse> pageCourseListQuery(Integer current, Integer size, FrontCourseQueryVo courseQuery) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseQuery.getSubjectParentId())) {
            wrapper.eq("subject_parent_id", courseQuery.getSubjectParentId());
        }

        if (!StringUtils.isEmpty(courseQuery.getSubjectId())) {
            wrapper.eq("subject_id", courseQuery.getSubjectId());
        }

        if (!StringUtils.isEmpty(courseQuery.getBuyCountSort())) {
            wrapper.orderByDesc("buy_count");
        }

        if (!StringUtils.isEmpty(courseQuery.getGmtCreateSort())) {
            wrapper.orderByDesc("gmt_create");
        }

        if (!StringUtils.isEmpty(courseQuery.getPrice())) {
            wrapper.orderByDesc("price");
        }
        Page<EduCourse> coursePage = new Page<>(current, size);
        IPage<EduCourse> eduCourseIPage = this.page(coursePage, wrapper);
        return eduCourseIPage;
    }

    //课程详情页面显示
    //@Cacheable(value = "courseInfo", key = "#id")
    @Override
    public FrontCourseVo findCourseInfoById(String id) {
        FrontCourseVo course = baseMapper.findCourseInfoById(id);
        List<ChapterVo> chapters = eduChapterService.findAll(id);
        course.setChapters(chapters);
        this.updatePageViewCount(id);
        return course;
    }

    //课程浏览数
    @Override
    public void updatePageViewCount(String id) {
        EduCourse course = baseMapper.selectById(id);
        course.setViewCount(course.getViewCount() + 1);
        baseMapper.updateById(course);
    }

}
