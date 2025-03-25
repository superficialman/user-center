package com.bytebreeze.usercenterboot.constant;

/**
 * @author makabaka
 * @date 2025/3/12
 * @description 存储用户操作需要的常量
 */
public interface UserConstant {

    /**
     * session的键
     */
    String USER_LOGIN_STATE = "userLoginState";

    /**
     * 用户角色
     * 普通用户：DEFAULT_ROLE
     * 管理员：ADMIN_ROLE
     */
    int DEFAULT_ROLE = 0;
    int ADMIN_ROLE = 1;

}
