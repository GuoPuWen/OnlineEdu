package com.guli.service_edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guli.service_edu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.service_edu.entity.vo.TeacherQueryVo;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author
 * @since 2020-09-28
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 组合查询返回结果
     */
    public IPage<EduTeacher> pageTeacherQuery(Integer current, Integer size, TeacherQueryVo teacherQuery);

}
