package com.java;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.service_edu.EduApplication;
import com.guli.service_edu.entity.EduSubject;
import com.guli.service_edu.service.EduSubjectService;
import com.guli.service_edu.service.impl.EduSubjectServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Deque;
import java.util.LinkedList;
import java.util.UUID;


/**
 * @author 四五又十
 * @version 1.0
 * @date 2020/10/5 17:56
 */
@SpringBootTest(classes = EduApplication.class)
@RunWith(SpringRunner.class)
public class test {
    int i;

    @Autowired
    private EduSubjectService eduSubjectService;

    /**
     * 测试插入新数据，mybatis是否可以返回自动插入字段
     */
    @Test
    public void testMpAutoInsert() {
        EduSubject oneSubject = new EduSubject();
        oneSubject.setParentId("0");
        oneSubject.setTitle("网络安全");
        eduSubjectService.save(oneSubject);
        System.out.println(oneSubject);

    }

    @Test
    public void test() {
        String s = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(s);
    }


}
