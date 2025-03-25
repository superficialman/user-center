package com.bytebreeze.usercenterboot.entity.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author makabaka
 * @date 2025/3/12
 * @description 用于接受前端注册参数JSON格式
 */
@Data
public class UserRegisterRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String account;
    private String password;
    private String checkPassword;

}
