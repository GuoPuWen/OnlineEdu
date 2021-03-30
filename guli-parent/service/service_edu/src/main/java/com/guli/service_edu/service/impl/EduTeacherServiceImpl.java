package com.guli.service_edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.service_edu.entity.EduTeacher;
import com.guli.service_edu.entity.vo.TeacherQueryVo;
import com.guli.service_edu.mapper.EduTeacherMapper;
import com.guli.service_edu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author
 * @since 2020-09-28
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Autowired
    private EduTeacherMapper eduTeacherMapper;

    @Override
    public IPage<EduTeacher> pageTeacherQuery(Integer current, Integer size, TeacherQueryVo teacherQueryVo) {
        QueryWrapper<EduTeacher> eduTeacherQueryWrapper = new QueryWrapper<>();
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String begin = teacherQueryVo.getBegin();
        String end = teacherQueryVo.getEnd();
        if (!StringUtils.isEmpty(name)) {
            eduTeacherQueryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            eduTeacherQueryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            eduTeacherQueryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            eduTeacherQueryWrapper.le("gmt_create", end);
        }
//        List<EduTeacher> eduTeachers = eduTeacherMapper.selectList(eduTeacherQueryWrapper);
        eduTeacherQueryWrapper.orderByDesc("gmt_create");
        Page<EduTeacher> eduTeacherPage = new Page<>(current, size);
        IPage<EduTeacher> page = this.page(eduTeacherPage, eduTeacherQueryWrapper);
        return page;
    }
}
