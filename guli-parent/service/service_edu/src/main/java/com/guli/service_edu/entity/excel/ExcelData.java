package com.guli.service_edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author 四五又十
 * @version 1.0
 * @date 2020/10/4 22:35
 */
@Data
public class ExcelData {
    @ExcelProperty(index = 0)
    private int sno;

    @ExcelProperty(index = 1)
    private String sname;
}
