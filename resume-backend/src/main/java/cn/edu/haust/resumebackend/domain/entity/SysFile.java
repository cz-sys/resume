package cn.edu.haust.resumebackend.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;

/**
 * @author chengzhen
 * created_at 2025/5/11
 */
@TableName("tbl_sys_file")
@Data
@Builder
public class SysFile {
    @TableId(value = "file_id", type = IdType.AUTO)
    private Long fileId;
    // 模块名
    private String module;
    // 文件名
    private String originalName;
    // 文件后缀
    private String suffix;
    // 存储名
    private String storeName;
    // 存储路径
    private String storePath;
    // 文件大小
    private Long fileSize;
    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdAt;
    // 创建人
    private Integer createdBy;
    // 修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedAt;
}