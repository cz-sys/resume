package cn.edu.haust.resumebackend.exception;

import cn.edu.haust.resumebackend.utils.ResultCodeWithMsg;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chengzhen
 * created_at 2025/5/2
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {
    private String code;
    private String msg;
    public BusinessException(ResultCodeWithMsg resultCodeWithMsg) {
        this.code = resultCodeWithMsg.getCode();
        this.msg = resultCodeWithMsg.getMsg();
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
