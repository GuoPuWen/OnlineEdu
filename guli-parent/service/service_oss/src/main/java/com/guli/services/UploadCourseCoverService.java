package com.guli.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 四五又十
 * @create 2020/10/4 1:56
 */
public interface UploadCourseCoverService {
    String upload(MultipartFile file);
}
