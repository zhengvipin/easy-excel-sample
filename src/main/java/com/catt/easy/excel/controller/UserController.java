package com.catt.easy.excel.controller;

import com.alibaba.excel.EasyExcel;
import com.catt.easy.excel.entity.User;
import com.catt.easy.excel.util.EasyExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息 前端控制器
 *
 * @author zwp
 * @since 2019-12-27 20:04
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * excel下载
     *
     * @param filename 文件名
     * @param response 相应体
     */
    @PostMapping("/export")
    public void exportExcel(@RequestParam("filename") String filename,
                            HttpServletResponse response) throws IOException {

        List<User> data = new ArrayList<>();
        data.add(new User(1, "张三", "男", "教师", "广州"));
        data.add(new User(2, "李四", "女", "记者", "上海"));
        data.add(new User(3, "王五", "男", "学生", "北京"));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        EasyExcel.write(bos, User.class).autoCloseStream(Boolean.FALSE).sheet("用户").doWrite(data);
        EasyExcelUtil.downloadFile(filename, bos, response);
    }
}
