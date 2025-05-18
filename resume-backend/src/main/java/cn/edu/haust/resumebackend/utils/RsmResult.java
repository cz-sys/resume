package cn.edu.haust.resumebackend.utils;

import cn.edu.haust.resumebackend.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chengzhen
 * created_at 2025/5/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RsmResult {
    private boolean success = true;
    private String code;
    private String msg;
    private Object data;

    public RsmResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static RsmResult ok(ResultCodeWithMsg resultEnum) {
        return new RsmResult(resultEnum.getCode(), resultEnum.getMsg(), null);
    }

    public static RsmResult ok(ResultCodeWithMsg resultEnum, Object data) {
        return new RsmResult(resultEnum.getCode(), resultEnum.getMsg(), data);
    }

    public static RsmResult error(BusinessException businessException){
        return new RsmResult(false, businessException.getCode(), businessException.getMsg(), null);
    }
}
