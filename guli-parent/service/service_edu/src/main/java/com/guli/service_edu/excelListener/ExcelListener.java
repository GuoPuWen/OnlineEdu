package com.guli.service_edu.excelListener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.handler.GuliException;
import com.guli.service_edu.entity.EduSubject;
import com.guli.service_edu.entity.excel.ExcelData;
import com.guli.service_edu.entity.excel.ExcelSubjectData;
import com.guli.service_edu.service.EduSubjectService;

import java.util.*;

/**
 * @author 四五又十
 * @version 1.0
 * @date 2020/10/5 1:34
 */
public class ExcelListener extends AnalysisEventListener<ExcelSubjectData> {

    private EduSubjectService eduSubjectService;

    public ExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    public ExcelListener() {
    }

    @Override
    public void invoke(ExcelSubjectData data, AnalysisContext context) {
        //System.out.println("--------------------" + data + "----------------------");
        if (data.getOneSubject() == null) {
            throw new GuliException(20001, "一级分类不能为空");
        }

        //插入一级分类
        QueryWrapper<EduSubject> oneSubjectWrapper = new QueryWrapper<>();
        //上传一级分类
        oneSubjectWrapper.eq("parent_id", "0");
        oneSubjectWrapper.eq("title", data.getOneSubject());
        EduSubject isExitOneSubject = eduSubjectService.getOne(oneSubjectWrapper);

        if (isExitOneSubject == null) {
            isExitOneSubject = new EduSubject();
            isExitOneSubject.setParentId("0");
            isExitOneSubject.setTitle(data.getOneSubject());
            eduSubjectService.save(isExitOneSubject);
        }
        //注意：要想拿到mp执行sql后的自动插入的id值，那么对象一定是只能isExitOneSubject，这里有两种情况
        //1. isExitOneSubject为空，那么new EduSubject()将他存进表里
        //2. isExitOneSubject不为空，那么刚好二级分类可拿到该id值，作为它的parent_id                             值
        //插入二级分类
        String twoSubjectName = data.getTwoSubject();
        //可以只插入一级分类
        if (twoSubjectName != null) {
            QueryWrapper<EduSubject> twoSubjectWrapper = new QueryWrapper<>();
            twoSubjectWrapper.ne("parent_id", "0");
            twoSubjectWrapper.eq("title", twoSubjectName);
            EduSubject isTwoSubject = eduSubjectService.getOne(twoSubjectWrapper);
            if (isTwoSubject == null) {
                EduSubject twoSubject = new EduSubject();

                twoSubject.setTitle(twoSubjectName);
                twoSubject.setParentId(isExitOneSubject.getId());

                eduSubjectService.save(twoSubject);
                // System.out.println(twoSubject);
            }
        }


    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
