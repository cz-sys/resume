package cn.edu.haust.resumebackend.controller;

import cn.edu.haust.resumebackend.domain.entity.UserInfo;
import cn.edu.haust.resumebackend.service.UserInfoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chengzhen
 * created_at 2025/4/26
 */
@RestController
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;
    @RequestMapping("/getById")
    public UserInfo getById() {
        return userInfoService.getById(1);
    }
}
