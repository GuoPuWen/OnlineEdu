package com.guli.service_edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.handler.GuliException;
import com.guli.service_edu.entity.EduChapter;
import com.guli.service_edu.entity.EduVideo;
import com.guli.service_edu.entity.vo.ChapterVo;
import com.guli.service_edu.entity.vo.VideoVo;
import com.guli.service_edu.mapper.EduChapterMapper;
import com.guli.service_edu.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.service_edu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> findAll(String courseId) {

        List<ChapterVo> finalChapter = new ArrayList<>();

        //查询章节
        QueryWrapper<EduChapter> chapterWrapper = new QueryWrapper();
        chapterWrapper.eq("course_id", courseId);
        List<EduChapter> chapterList = this.list(chapterWrapper);

        //查询小节
        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id", courseId);
        List<EduVideo> eduVideos = eduVideoService.list(videoWrapper);

        for (EduChapter eduChapter : chapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);


            List<VideoVo> tempVideoList = new ArrayList<>();


            for (EduVideo eduVideo : eduVideos) {
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    VideoVo video = new VideoVo();
                    //System.out.println(eduVideo.getIsFree());
                    BeanUtils.copyProperties(eduVideo, video);
                    video.setIsFree(eduVideo.getIsFree());
                    tempVideoList.add(video);
                }
            }
            System.out.println(tempVideoList);
            chapterVo.setChildren(tempVideoList);
            finalChapter.add(chapterVo);
        }
        System.out.println(finalChapter);
        return finalChapter;
    }

    // 删除章节信息，如果章节下面有小节，那么不能进行删除
    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        if (eduVideoService.count(wrapper) > 0) {
            throw new GuliException(20001, "删除失败，该章节下有小节");
        }
        return this.removeById(chapterId);
    }

    @Override
    public boolean deleteChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        return this.remove(wrapper);
    }
}
