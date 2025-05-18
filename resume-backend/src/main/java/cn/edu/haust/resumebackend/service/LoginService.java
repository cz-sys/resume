package cn.edu.haust.resumebackend.service;

import cn.edu.haust.resumebackend.domain.entity.UserInfo;
import cn.edu.haust.resumebackend.utils.RsmResult;

/**
 * @author chengzhen
 * created_at 2025/4/29
 */
public interface LoginService {
    RsmResult login(UserInfo userInfo);
}
