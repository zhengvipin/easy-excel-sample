package com.catt.easy.excel.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.CharEncoding;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * excel导出工具类
 * </p>
 *
 * @author zwp
 * @since 2019-12-26
 */
@Slf4j
public class EasyExcelUtil {

    public static void downloadFile(String filename, ByteArrayOutputStream bos, HttpServletResponse response) {
        if (filename == null) {
            log.error("下载文件名不能为空！");
            throw new NullPointerException("下载文件名为空！");
        }

        // 文件名处理
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        filename = filename.matches("^.*?\\.(?:xls)x?$") ? filename : filename + ".xls";
        filename = filename.split("\\.")[0] + sdf.format(new Date()) + "." + filename.split("\\.")[1];

        try {
            filename = URLEncoder.encode(filename, CharEncoding.UTF_8);
        } catch (UnsupportedEncodingException e) {
            log.error("文件名转码失败！");
            e.printStackTrace();
        }

        response.setHeader(HttpHeaders.ACCEPT_CHARSET, CharEncoding.UTF_8);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename);

        try (OutputStream outputStream = response.getOutputStream()) {
            outputStream.write(bos.toByteArray() == null ? "".getBytes() : bos.toByteArray());
            outputStream.flush();
            bos.close();
        } catch (IOException e) {
            log.error("下载文件失败！");
            e.printStackTrace();
        }
    }
}
