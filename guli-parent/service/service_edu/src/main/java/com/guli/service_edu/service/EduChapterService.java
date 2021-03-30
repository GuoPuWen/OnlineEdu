package com.guli.service_edu.service;

import com.guli.service_edu.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.service_edu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author
 * @since 2020-10-07
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> findAll(String courseId);

    boolean deleteChapter(String chapterId);

    boolean deleteChapterByCourseId(String courseId);
}
