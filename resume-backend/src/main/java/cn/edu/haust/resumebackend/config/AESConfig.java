package cn.edu.haust.resumebackend.config;

import cn.edu.haust.resumebackend.utils.AESUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author chengzhen
 * created_at 2025/5/1
 */
@Configuration
@Slf4j
public class AESConfig {
    @Value("${aes.key}")
    private String aesKey;

    @PostConstruct
    public void init() {
        log.info("初始化AESConfig");
        AESUtils.setAesKey(aesKey);
    }
}
