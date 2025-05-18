package cn.edu.haust.resumebackend.service.impl;

import cn.edu.haust.resumebackend.domain.entity.UserInfo;
import cn.edu.haust.resumebackend.enums.AuthResultEnum;
import cn.edu.haust.resumebackend.exception.BusinessException;
import cn.edu.haust.resumebackend.mapper.UserInfoMapper;
import cn.edu.haust.resumebackend.service.RegisterService;
import cn.edu.haust.resumebackend.utils.AESUtils;
import cn.edu.haust.resumebackend.utils.RsmResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author chengzhen
 * created_at 2025/5/1
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public RsmResult register(UserInfo userInfo) {
        // 根据加密后的用户名查询用户，如果存在，抛出业务异常：用户名已存在
        if (userInfoMapper.exists(new QueryWrapper<UserInfo>().eq("username", AESUtils.encrypt(userInfo.getUsername())))) {
            throw new BusinessException(AuthResultEnum.USERNAME_HAS_EXIST);
        }
        // 用户名不存在，将用户信息添加到数据库，完成注册操作
        userInfo.setUsername(AESUtils.encrypt(userInfo.getUsername()));
        userInfo.setPassword(AESUtils.encrypt(userInfo.getPassword()));
        userInfoMapper.insert(userInfo);
        return RsmResult.ok(AuthResultEnum.REGISTER_SUCCESS);
    }
}
