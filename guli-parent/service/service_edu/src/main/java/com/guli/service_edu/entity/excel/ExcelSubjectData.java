package com.guli.service_edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author εδΊεε
 * @version 1.0
 * @date 2020/10/5 1:49
 */
@Data
public class ExcelSubjectData {
    @ExcelProperty(index = 0)
    private String oneSubject;

    @ExcelProperty(index = 1)
    private String twoSubject;
}
