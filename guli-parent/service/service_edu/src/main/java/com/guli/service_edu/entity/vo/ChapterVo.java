package com.guli.service_edu.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 四五又十
 * @version 1.0
 * @date 2020/10/8 19:55
 */
@Data
public class ChapterVo {
    private String id;
    private String title;
    private String flag;
    private List<VideoVo> children;
}
