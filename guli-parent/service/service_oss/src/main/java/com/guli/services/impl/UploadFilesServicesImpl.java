package com.guli.services.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.guli.services.UploadTeacherAvatarService;
import com.guli.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author 四五又十
 * @version 1.0
 * @date 2020/10/4 1:57
 */
@Service
public class UploadFilesServicesImpl implements UploadTeacherAvatarService {

    @Override
    public String upload(MultipartFile file) {

        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);


        try {
            InputStream inputStream = file.getInputStream();
            //https://four153.oss-cn-beijing.aliyuncs.com/01-%E5%86%85%E5%AE%B9%E7%9A%84%E4%BB%8B%E7%BB%8D.png
            String fileName = file.getOriginalFilename();
            //给上传的文件添加uuid
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            String date = new DateTime().toString("yyyy/MM/dd");
            fileName = date + uuid + fileName;
            //System.out.println(fileName);
            //System.out.println(fileName);
            String url = "https://" + ConstantPropertiesUtil.BUCKET_NAME + "." + endpoint + "/" + fileName;
            System.out.println(url);
            PutObjectRequest putObjectRequest = new PutObjectRequest(ConstantPropertiesUtil.BUCKET_NAME, fileName, inputStream);
            ossClient.putObject(putObjectRequest);
            return url;
        } catch (IOException e) {
            ossClient.shutdown();
            return null;
        }
    }
}
