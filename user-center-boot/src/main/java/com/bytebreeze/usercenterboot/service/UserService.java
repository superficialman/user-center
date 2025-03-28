package com.bytebreeze.usercenterboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bytebreeze.usercenterboot.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
* @author Liu Qiang
* @description 针对表【user】的数据库操作Service
* @createDate 2025-03-10 19:02:15
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param account       账号
     * @param password      密码
     * @param checkPassword 二次校验密码
     * @return 新用户id
     */
    Long userRegister(String account, String password, String checkPassword);

    /**
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @return 脱敏后的用户信息
     */
    User userLogin(String account, String password, HttpServletRequest request);

    /**
     * 获取当前用户
     * @param request 前端请求
     * @return 当前用户
     */
    User getCurrentUser(HttpServletRequest request);

    /**
     * 用户注销
     * @param request 前端请求
     * @return 是否注销成功
     */
    Boolean userLogout(HttpServletRequest request);

    /**
     * 用户查询
     * @param username 用户名
     * @return 返回查询到的用户列表
     */
    List<User> userSelect(String username, HttpServletRequest request);

    /**
     * 所有用户查询
     * @return 返回查询到的用户列表
     */
    List<User> getAllUser(HttpServletRequest request);

    /**
     * 用户删除
     * @param id 用户id
     * @return 返回用户是否被删除
     */
    Boolean userDelete(long id, HttpServletRequest request);

    /**
     * ---验证是否为管理员---
     * @param request 前端请求
     * @return 如果非管理员返回true
     */
    Boolean isNotAdmin(HttpServletRequest request);
}
