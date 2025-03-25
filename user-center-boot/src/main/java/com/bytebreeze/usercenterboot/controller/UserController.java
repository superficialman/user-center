package com.bytebreeze.usercenterboot.controller;

import com.bytebreeze.usercenterboot.common.BaseResponse;
import com.bytebreeze.usercenterboot.common.ErrorCode;
import com.bytebreeze.usercenterboot.exception.BusinessException;
import com.bytebreeze.usercenterboot.entity.User;
import com.bytebreeze.usercenterboot.entity.request.UserLoginRequest;
import com.bytebreeze.usercenterboot.entity.request.UserRegisterRequest;
import com.bytebreeze.usercenterboot.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author makabaka
 * @date 2025/3/11
 * @description 用户请求控制层接口
 * 全部以通用返回类BaseResponse返回
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/user")
public class UserController {
    /**
     * 用户服务层接口
     */
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param userRegisterRequest 用户注册请求
     * @return 用户id
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        //请求参数为空
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR, "请求参数不能为空");
        }

        //简单校验，如果有空值，不进业务层
        String account = userRegisterRequest.getAccount();
        String password = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(account, password, checkPassword)) {
            throw new BusinessException(ErrorCode.NULL_ERROR, "账号和密码不能为空");
        }
        //进入业务层处理，返回用户id
        Long userId = userService.userRegister(account, password, checkPassword);
        //返回全局通用返回类
        return BaseResponse.success(userId);
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest 用户登录请求
     * @param request          前端请求
     * @return 脱敏后的用户信息
     */
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        //请求参数为空
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR, "请求参数不能为空");
        }
        //简单校验，如果有空值，不进业务层
        String account = userLoginRequest.getAccount();
        String password = userLoginRequest.getPassword();
        if (StringUtils.isAnyBlank(account, password)) {
            throw new BusinessException(ErrorCode.NULL_ERROR, "账号和密码不能为空");
        }
        User user = userService.userLogin(account, password, request);
        return BaseResponse.success(user);
    }

    /**
     * 获取当前用户
     *
     * @param request 前端请求
     * @return 当前用户
     */
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        User currentUser = userService.getCurrentUser(request);
        return BaseResponse.success(currentUser);
    }

    /**
     * 用户注销
     *
     * @param request 前端请求
     * @return 是否注销成功
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        Boolean result = userService.userLogout(request);
        return BaseResponse.success(result);
    }

    /**
     * 用户查询
     *
     * @param username 用户名
     * @param request  前端请求
     * @return 查询到的用户列表
     */
    @GetMapping("/select")
    public BaseResponse<List<User>> userSelect(@RequestParam String username, HttpServletRequest request) {
        List<User> userList = userService.userSelect(username, request);
        return BaseResponse.success(userList);
    }

    /**
     * 查询所有用户
     */
    @GetMapping("/allUser")
    public BaseResponse<List<User>> userSelect(HttpServletRequest request) {
        List<User> userList = userService.getAllUser(request);
        return BaseResponse.success(userList);
    }

    /**
     * 用户删除
     *
     * @param id      用户id
     * @param request 前端请求
     * @return 用户是否被删除
     */
    @PostMapping("/delete/{id}")
    public BaseResponse<Boolean> userDelete(@PathVariable Long id, HttpServletRequest request) {
        Boolean result = userService.userDelete(id, request);
        return BaseResponse.success(result);
    }
}
