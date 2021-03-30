package com.guli.service_edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.handler.GuliException;
import com.guli.service_edu.entity.EduSubject;
import com.guli.service_edu.entity.vo.OneSubject;
import com.guli.service_edu.entity.vo.TwoSubject;
import com.guli.service_edu.entity.excel.ExcelSubjectData;
import com.guli.service_edu.excelListener.ExcelListener;
import com.guli.service_edu.mapper.EduSubjectMapper;
import com.guli.service_edu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author
 * @since 2020-10-05
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void upload(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            InputStream fileInputStream = file.getInputStream();
            EasyExcel.read(fileInputStream, ExcelSubjectData.class, new ExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            throw new GuliException(20001, "数据不能为空");

        }

    }

    @Override
    public List<OneSubject> findAll() {
        List<OneSubject> finalResult = new ArrayList<>();
        //查询一级分类
        QueryWrapper<EduSubject> oneSubjectWrapper = new QueryWrapper<>();
        oneSubjectWrapper.eq("parent_id", "0");
        List<EduSubject> oneSubjectLists = this.list(oneSubjectWrapper);
        for (EduSubject oneSubjectList : oneSubjectLists) {
            //新建一个一级分类 类
            OneSubject oneSubject = new OneSubject();
            //新建一个一级分类下的二级分类list集合
            List<TwoSubject> oneTwoSubjectList = new ArrayList<>();
            //将id title封装进一级分类下
            oneSubject.setId(oneSubjectList.getId());
            oneSubject.setTitle(oneSubjectList.getTitle());
            //查询二级分类
            QueryWrapper<EduSubject> twoSubjectWrapper = new QueryWrapper<>();
            twoSubjectWrapper.eq("parent_id", oneSubjectList.getId());
            List<EduSubject> twoSubjectLists = this.list(twoSubjectWrapper);
            //遍历一级分类下的二级分类
            for (EduSubject twoSubjectList : twoSubjectLists) {
                TwoSubject twoSubject = new TwoSubject();
                twoSubject.setId(twoSubjectList.getId());
                twoSubject.setTitle(twoSubjectList.getTitle());
                oneTwoSubjectList.add(twoSubject);
            }
            oneSubject.setTwoSubjects(oneTwoSubjectList);
            finalResult.add(oneSubject);
        }
        return finalResult;

    }


}
