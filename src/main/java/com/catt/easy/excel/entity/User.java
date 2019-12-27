package com.catt.easy.excel.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表
 *
 * @author zwp
 * @since 2019-12-27 20:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ContentRowHeight(value = 20)
@HeadRowHeight(30)
@ColumnWidth(25)
public class User {

    @ExcelIgnore
    private Integer id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("性别")
    private String sex;

    @ExcelProperty("职业")
    private String job;

    @ColumnWidth(30)
    @ExcelProperty("居住地址")
    private String address;
}
