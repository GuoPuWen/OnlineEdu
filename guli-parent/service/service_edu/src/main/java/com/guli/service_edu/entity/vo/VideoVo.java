package com.guli.service_edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 四五又十
 * @version 1.0
 * @date 2020/10/8 19:55
 */
@Data
public class VideoVo {
    private String id;
    private String title;
    private String flag;
    private Integer isFree;
    @ApiModelProperty(value = "云服务器上存储的视频文件名称")
    private String videoOriginalName;
}
