package com.bytebreeze.usercenterboot.common;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @description 全局通用返回类
 * @author makabaka
 * @date 2025/3/18
 */

@Data
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {

    /**
     * 序列化版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 返回状态码
     */
    private int code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回描述(详情)
     */
    private String description;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 请求成功
     * @param data 返回数据
     * @param <T> 返回数据类型
     * @return BaseResponse
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(200, "请求成功", null, data);
    }

    /**
     * 请求失败
     * @param errorCode 错误码
     * @return BaseResponse
     */
    public static BaseResponse<?> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode.getCode(), errorCode.getMessage(), errorCode.getDescription(), null);
    }

    // 自定义详细描述
    public static BaseResponse<?> error(ErrorCode errorCode, String description) {
        return new BaseResponse<>(errorCode.getCode(), errorCode.getMessage(), description, null);
    }

    // 自定义消息和详细描述
    public static BaseResponse<?> error(ErrorCode errorCode, String message, String description) {
        return new BaseResponse<>(errorCode.getCode(), message, description, null);
    }

    // 全部自定义
    public static BaseResponse<?> error(int errorCode, String message, String description) {
        return new BaseResponse<>(errorCode, message, description, null);
    }

}
