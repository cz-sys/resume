package cn.edu.haust.resumebackend.test;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @author chengzhen
 * created_at 2025/5/1
 */
public class MybatisPlusGenerator {

    public static void main(String[] args) {
        // 使用 FastAutoGenerator 快速配置代码生成器
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/resume?serverTimezone=GMT%2B8", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("chengzhen") // 设置作者
                            .outputDir("src\\main\\java"); // 输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("cn.edu.haust.resumebackend") // 设置父包名
                            .entity("domain.entity") // 设置实体类包名
                            .mapper("mapper") // 设置 Mapper 接口包名
                            .service("service") // 设置 Service 接口包名
                            .serviceImpl("service.impl") // 设置 Service 实现类包名
                            .xml("mapper"); // 设置 Mapper XML 文件包名
                })
                .strategyConfig(builder -> {
                    builder.addInclude("token") // 设置需要生成的表名
                            .entityBuilder()
                            .enableLombok() // 启用 Lombok
                            .enableTableFieldAnnotation() // 启用字段注解
                            .controllerBuilder()
                            .enableRestStyle(); // 启用 REST 风格
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用 Freemarker 模板引擎
                .execute(); // 执行生成
    }

}
