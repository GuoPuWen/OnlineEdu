package com.guli.service_edu.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 四五又十
 * @version 1.0
 * @date 2020/10/6 15:53
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    private List<TwoSubject> twoSubjects;
}
