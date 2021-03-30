package com.guli.service_edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.service_edu.client.VodClient;
import com.guli.service_edu.entity.EduVideo;
import com.guli.service_edu.mapper.EduVideoMapper;
import com.guli.service_edu.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Queue;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author
 * @since 2020-10-07
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    @Autowired
    private VodClient vodClient;

    @Override
    public boolean deleteVideoByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        return this.remove(wrapper);
    }

    //删除小节的方法，在删除小节的同时删除视频
    @Override
    public boolean deleteVideoByVideoId(String videoId) {
        //根据id查询出视频id
        EduVideo video = this.getById(videoId);
        String aliyunVideoId = video.getVideoSourceId();
        System.out.println(aliyunVideoId);
        if (!StringUtils.isEmpty(aliyunVideoId)) {
            vodClient.removeVideo(aliyunVideoId);
        }
        //删除小节
        return this.removeById(videoId);
    }
}
