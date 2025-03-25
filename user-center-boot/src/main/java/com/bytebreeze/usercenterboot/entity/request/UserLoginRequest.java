package com.bytebreeze.usercenterboot.entity.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author makabaka
 * @date 2025/3/12
 * @description
 */
@Data
public class UserLoginRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String account;
    private String password;
}
