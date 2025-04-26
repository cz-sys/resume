package cn.edu.haust.resumebackend.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author chengzhen
 * created_at 2025/4/26
 */
@Data
@TableName("user_info")
public class UserInfo {
    // 用户id
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    // 用户名
    private String username;

    // 邮箱
    private String email;

    // 密码
    private String password;

    // 名字
    private String firstName;

    // 姓氏
    private String lastName;

    // 出生日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    // 更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}

