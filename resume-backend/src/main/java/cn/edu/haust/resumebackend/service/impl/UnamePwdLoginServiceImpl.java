package cn.edu.haust.resumebackend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.edu.haust.resumebackend.domain.entity.UserInfo;
import cn.edu.haust.resumebackend.enums.AuthResultEnum;
import cn.edu.haust.resumebackend.exception.BusinessException;
import cn.edu.haust.resumebackend.mapper.UserInfoMapper;
import cn.edu.haust.resumebackend.service.LoginService;
import cn.edu.haust.resumebackend.utils.AESUtils;
import cn.edu.haust.resumebackend.utils.RsmResult;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author chengzhen
 * created_at 2025/4/29
 * 用户名密码登录实现类
 */
@Service(value = "unamePwdLogin")
public class UnamePwdLoginServiceImpl implements LoginService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public RsmResult login(UserInfo userInfo) {
        // 根据加密后的用户名和密码查询用户
        UserInfo userInfoGet = userInfoMapper.selectOne(
                new LambdaQueryWrapper<UserInfo>()
                        .eq(UserInfo::getUsername, AESUtils.encrypt(userInfo.getUsername()))
                        .eq(UserInfo::getPassword, AESUtils.encrypt(userInfo.getPassword()))
        );
        // 查询结果不存在，抛出业务异常：用户名密码错误
        if (ObjectUtil.isEmpty(userInfoGet)) {
            throw new BusinessException(AuthResultEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        // 查询结果存在，使用 SaToken 执行登录操作
        StpUtil.login(userInfoGet.getUserId());
        // 返回 Token 信息
        return RsmResult.ok(AuthResultEnum.LOGIN_SUCCESS, StpUtil.getTokenInfo());
    }
}
