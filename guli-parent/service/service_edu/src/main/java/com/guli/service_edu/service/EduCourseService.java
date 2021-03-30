package com.guli.service_edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guli.service_edu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.service_edu.entity.EduTeacher;
import com.guli.service_edu.entity.vo.*;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author
 * @since 2020-10-07
 */
public interface EduCourseService extends IService<EduCourse> {

    String addCourse(CourseInfoVo courseInfoVo);

    CourseInfoVo findById(String courseId);

    int updatebyId(CourseInfoVo courseInfoVo);

    CoursePublishVo findCoursePublishById(String courseId);

    boolean publishCourse(String courseId);

    IPage<EduCourse> courseQuery(CourseQueryVo courseQuery, Integer current, Integer size);

    boolean deleteCourse(String courseId);

    List<EduCourse> getCourseList();

    IPage<EduCourse> pageCourseListQuery(Integer current, Integer size, FrontCourseQueryVo frontCourseQueryVo);

    FrontCourseVo findCourseInfoById(String id);

    void updatePageViewCount(String id);
}
