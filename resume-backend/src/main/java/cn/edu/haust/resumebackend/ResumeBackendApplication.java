package cn.edu.haust.resumebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chengzhen
 */
@SpringBootApplication
@MapperScan("cn.edu.haust.resumebackend.mapper")
public class ResumeBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResumeBackendApplication.class, args);
    }

}
