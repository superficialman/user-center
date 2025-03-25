package com.bytebreeze.usercenterboot.exception;

import com.bytebreeze.usercenterboot.common.ErrorCode;
import lombok.Getter;

import java.io.Serial;

/**
 * @author LQ
 * @date 2025/3/18
 * @description 自定义异常
 * 主动抛出的异常, 用于业务逻辑处理, 由全局异常处理器GlobalExceptionHandle捕获并返回给前端
 */
@Getter
public class BusinessException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;
  private final int code;
  private final String description;

  // 自定义错误码，错误信息，错误描述
  public BusinessException(int code, String message, String description) {
    super(message);
    this.code = code;
    this.description = description;
  }

  // 使用ErrorCode枚举类
  public BusinessException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.code = errorCode.getCode();
    this.description = errorCode.getDescription();
  }

  // 使用ErrorCode枚举类，自定义错误描述
  public BusinessException(ErrorCode errorCode, String description) {
    super(errorCode.getMessage());
    this.code = errorCode.getCode();
    this.description = description;
  }

}
