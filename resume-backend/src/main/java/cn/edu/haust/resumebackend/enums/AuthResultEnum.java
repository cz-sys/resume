package cn.edu.haust.resumebackend.enums;

import cn.edu.haust.resumebackend.utils.ResultCodeWithMsg;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chengzhen
 * created_at 2025/5/2
 */
@Getter
@AllArgsConstructor
public enum AuthResultEnum implements ResultCodeWithMsg {
    LOGIN_SUCCESS("AUTH-1000", "登录成功"),
    REGISTER_SUCCESS("AUTH-1002", "注册成功"),
    USERNAME_HAS_EXIST("AUTH-1003", "用户名已存在"),
    USERNAME_OR_PASSWORD_ERROR("AUTH-1004", "用户名或密码错误"),
    NOT_LOGIN("AUTH-1005", "请先登录"),
    ;

    private final String code;
    private final String msg;
}
