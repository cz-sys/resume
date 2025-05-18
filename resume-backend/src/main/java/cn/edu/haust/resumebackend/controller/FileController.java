package cn.edu.haust.resumebackend.controller;

import cn.edu.haust.resumebackend.service.FileService;
import cn.edu.haust.resumebackend.utils.RsmResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chengzhen
 * created_at 2025/5/6
 */
@RestController
@Slf4j
public class FileController {
    @Resource
    private FileService fileService;
    /**
     * 文件上传
     * @param file 上传的文件
     * @param module 文件所属模块
     * @return 上传结果，文件上传完成之后的基本信息
     */
    @PostMapping("/file/upload")
    public RsmResult upload(MultipartFile file, String module) {
        return fileService.uploadFile(file, module);
    }
}
