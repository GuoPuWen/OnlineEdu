package com.guli.service_edu.service;

import com.guli.service_edu.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author
 * @since 2020-10-07
 */
public interface EduVideoService extends IService<EduVideo> {
    boolean deleteVideoByCourseId(String courseId);

    boolean deleteVideoByVideoId(String videoId);

}
