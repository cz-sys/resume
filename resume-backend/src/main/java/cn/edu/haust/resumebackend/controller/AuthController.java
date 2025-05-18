package cn.edu.haust.resumebackend.controller;

import cn.edu.haust.resumebackend.domain.entity.UserInfo;
import cn.edu.haust.resumebackend.service.LoginService;
import cn.edu.haust.resumebackend.service.RegisterService;
import cn.edu.haust.resumebackend.utils.RsmResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chengzhen
 * created_at 2025/4/29
 * 认证相关控制器
 */
@RestController
@Slf4j
public class AuthController {
    @Resource(name = "unamePwdLogin")
    private LoginService unamePwdLogin;
    @Resource
    private RegisterService registerService;
    @PostMapping("/auth/login")
    public RsmResult login(UserInfo userInfo) {
        return unamePwdLogin.login(userInfo);
    }
    @PostMapping("/auth/register")
    public RsmResult register(UserInfo userInfo) {
        return registerService.register(userInfo);
    }
}
