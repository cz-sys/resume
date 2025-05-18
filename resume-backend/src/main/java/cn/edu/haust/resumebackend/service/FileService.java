package cn.edu.haust.resumebackend.service;

import cn.edu.haust.resumebackend.utils.RsmResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chengzhen
 * created_at 2025/5/10
 */
public interface FileService {
    RsmResult  uploadFile(MultipartFile file, String module);
}
