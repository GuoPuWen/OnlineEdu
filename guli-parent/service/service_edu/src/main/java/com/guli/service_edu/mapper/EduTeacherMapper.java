package com.guli.service_edu.mapper;

import com.guli.service_edu.entity.EduTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author
 * @since 2020-09-28
 */
@Mapper
public interface EduTeacherMapper extends BaseMapper<EduTeacher> {

}
