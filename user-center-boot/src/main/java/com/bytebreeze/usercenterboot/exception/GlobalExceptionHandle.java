package com.bytebreeze.usercenterboot.exception;

import com.bytebreeze.usercenterboot.common.BaseResponse;
import com.bytebreeze.usercenterboot.common.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author LQ
 * @date 2025/3/18
 * @description 全局异常处理器
 * 捕获全局异常, 并返回给前端
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandle {

    /**
     * 业务异常处理
     * @param e 业务异常
     * @return BaseResponse
     * 捕获BusinessException异常, 并以BaseResponse返回给前端
     * 利用了spring的AOP机制，通过@ExceptionHandler注解，可以捕获指定的异常，然后统一处理
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandle(BusinessException e) {
        log.error("BusinessException:{}->{}", e.getMessage(), e.getDescription(), e);
        return BaseResponse.error(e.getCode(), e.getMessage(), e.getDescription());
    }

    /**
     * 运行时异常处理
     * @param e 运行时异常
     * @return BaseResponse
     * 捕获RuntimeException异常, 并以BaseResponse返回给前端
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runTimeExceptionHandle(RuntimeException e) {
        log.error("RunTimeException:运行时异常", e);
        return BaseResponse.error(ErrorCode.SYSTEM_ERROR, e.getMessage(),"");
    }
}
