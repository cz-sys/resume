package cn.edu.haust.resumebackend.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.SaTokenException;
import cn.edu.haust.resumebackend.enums.AuthResultEnum;
import cn.edu.haust.resumebackend.enums.CommonResultEnum;
import cn.edu.haust.resumebackend.utils.RsmResult;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author chengzhen
 * created_at 2025/5/2
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandle {
    @ExceptionHandler(BusinessException.class)
    public RsmResult handleBusinessException(HttpServletRequest request, BusinessException e) {
        businessExceptionLogRecord(request, e);
        return RsmResult.error(e);
    }

    @ExceptionHandler(NotLoginException.class)
    public RsmResult handleNotLoginException(HttpServletRequest request, NotLoginException e) {
        saTokenExceptionLogRecord(request, e);
        return RsmResult.ok(AuthResultEnum.NOT_LOGIN);
    }

    @ExceptionHandler(Exception.class)
    public RsmResult handleException(HttpServletRequest request, Exception e) {
        log.error("{} request error, ", request.getRequestURI(), e);
        return RsmResult.ok(CommonResultEnum.ERROR);
    }

    /**
     *  SaToken 异常日志记录方法
     */
    private static void saTokenExceptionLogRecord(HttpServletRequest request, SaTokenException e) {
        log.warn("auth fail, request url: {}", request.getRequestURI(), e);
    }

    /**
     * 业务异常日志记录方法
     */
    private static void businessExceptionLogRecord(HttpServletRequest request, BusinessException e) {
        log.warn("business fail, request url: {}, errorCode: {}, errorMsg: {}", request.getRequestURI(), e.getCode(), e.getMessage(), e);
    }
}
