package com.java.EasyExcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.property.ExcelWriteHeadProperty;
import com.guli.service_edu.entity.excel.ExcelData;
import com.guli.service_edu.excelListener.ExcelListener;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 四五又十
 * @version 1.0
 * @date 2020/10/4 22:25
 */
public class TestEasyExcel {
    @Test
    public void testExcelWrite() {
        String filename = "D:\\01.xlsx";
        List<ExcelData> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExcelData data1 = new ExcelData();
            data1.setSname("zhangsan" + i);
            data1.setSno(i);
            data.add(data1);
        }
        System.out.println(data);
        EasyExcel.write(filename, ExcelData.class).sheet("sheet1").doWrite(data);

    }

    @Test
    public void testExcelRead() {
        String filename = "D:\\01.xlsx";
        EasyExcel.read(filename, ExcelData.class, new ExcelListener()).sheet("sheet1").doRead();
        System.out.println();
    }

}
