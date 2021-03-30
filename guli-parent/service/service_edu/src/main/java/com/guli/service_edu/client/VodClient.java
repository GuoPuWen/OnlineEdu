package com.guli.service_edu.client;

import com.guli.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 四五又十
 * @version 1.0
 * @date 2020/10/15 15:54
 */
@FeignClient("service-vod")
@Component
public interface VodClient {
    @DeleteMapping("/eduvod/video/delete/{id}")
    public R removeVideo(@PathVariable String id);
}
