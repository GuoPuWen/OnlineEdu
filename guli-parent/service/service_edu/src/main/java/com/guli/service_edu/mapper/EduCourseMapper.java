package com.guli.service_edu.mapper;

import com.guli.service_edu.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guli.service_edu.entity.vo.CoursePublishVo;
import com.guli.service_edu.entity.vo.FrontCourseVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author
 * @since 2020-10-07
 */

public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo findCoursePublishById(String courseId);

    FrontCourseVo findCourseInfoById(String id);
}
