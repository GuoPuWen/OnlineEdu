package com.guli.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author εδΊεε
 * @version 1.0
 * @date 2020/10/12 17:00
 */
public interface VideoService {
    String uploadVideo(MultipartFile file);

    void removeVideo(String id);
}
