package com.guli.service_edu.service;

import com.guli.service_edu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.service_edu.entity.vo.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author
 * @since 2020-10-05
 */
public interface EduSubjectService extends IService<EduSubject> {

    void upload(MultipartFile file, EduSubjectService eduSubjectService);

    List<OneSubject> findAll();
}
