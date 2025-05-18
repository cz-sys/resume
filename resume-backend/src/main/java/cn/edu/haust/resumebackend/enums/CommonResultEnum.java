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
public enum CommonResultEnum implements ResultCodeWithMsg {
    OK("COMMON-200", "请求成功"),
    ERROR("COMMON-500", "系统异常");

    private final String code;
    private final String msg;
}
