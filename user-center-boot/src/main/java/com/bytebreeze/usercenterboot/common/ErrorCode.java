package com.bytebreeze.usercenterboot.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LQ
 * @date 2025/3/18
 * @description 自定义的一套错误码规范
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    PARAM_ERROR(40000, "请求参数错误", ""),
    NULL_ERROR(40001, "请求参数为空", ""),
    NO_LOGIN(40100, "未登录", ""),
    NO_AUTH(40101, "无权限", ""),
    SYSTEM_ERROR(50000, "系统错误", "");

    private final int code; // 错误码
    private final String message; // 错误信息
    private final String description; // 错误描述

}
