package cn.edu.haust.resumebackend.domain.entity;

import cn.dev33.satoken.session.SaSession;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author chengzhen
 * created_at 2025/5/2
 */
@TableName("sa_token_mysql")
@Data
@Builder
public class SaTokenMysqlData {
    @TableId("id")
    private Long id;

    // token
    private String tokenKey;

    // sa-token 的 session
    private SaSession tokenSession;

    // sa-token 的 token string
    private String string;

    private LocalDateTime expireAt;
}
