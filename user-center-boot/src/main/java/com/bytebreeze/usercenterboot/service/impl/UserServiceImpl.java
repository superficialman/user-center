package com.bytebreeze.usercenterboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytebreeze.usercenterboot.common.ErrorCode;
import com.bytebreeze.usercenterboot.exception.BusinessException;
import com.bytebreeze.usercenterboot.entity.User;
import com.bytebreeze.usercenterboot.mapper.UserMapper;
import com.bytebreeze.usercenterboot.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bytebreeze.usercenterboot.constant.UserConstant.ADMIN_ROLE;
import static com.bytebreeze.usercenterboot.constant.UserConstant.USER_LOGIN_STATE;


/**
* service实现类 - 用户
* @author 20629
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-03-10 19:02:15
*/

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "makabaka";

    /**
     * 注册接口
     *
     * @param account       账号
     * @param password      密码
     * @param checkPassword 二次校验密码
     * @return 用户注册返回id
     */
    @Override
    public Long userRegister(String account, String password, String checkPassword) {
        //1.校验
        //请求参数非空：账号，密码，校验密码
        if(StringUtils.isAnyBlank(account,password,checkPassword)){
            //抛出异常，由全局异常处理器GlobalExceptionHandle处理
            throw new BusinessException(ErrorCode.NULL_ERROR, "账号和密码不能为空");
        }
        //账号不小于四位，不大于20位
        if(account.length()<4 || account.length()>20){
            throw new BusinessException(ErrorCode.PARAM_ERROR, "账号长度不符合要求");
        }
        //密码不小于8位，不大于20位
        if(password.length()<8 || password.length()>20){
            throw new BusinessException(ErrorCode.PARAM_ERROR, "密码长度不符合要求");
        }
        //账号只包含字母，数字，下划线
        Matcher matcher = Pattern.compile("^[a-zA-Z0-9_]+$").matcher(account);
        if(!matcher.matches()){
            throw new BusinessException(ErrorCode.PARAM_ERROR, "账号只能包含字母，数字，下划线");
        }
        //密码和校验密码相同
        if(!checkPassword.equals(password)){
            throw new BusinessException(ErrorCode.PARAM_ERROR, "两次密码不一致");
        }
        //账号不能重复
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("account", account);
        long userCount = this.count(userQueryWrapper);
        if(userCount>0){
            throw new BusinessException(ErrorCode.PARAM_ERROR, "账号已存在");
        }
        //2.密码加密
        String encodedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        //3.插入到数据库
        User user = new User();
        user.setAccount(account);
        user.setPassword(encodedPassword);
        boolean save = this.save(user);
        if(!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败");
        }
        //4.插入成功
        return user.getId();
    }

    /**
     * 登录接口
     *
     * @param account  账号
     * @param password 密码
     * @param request  前端请求
     * @return 用户登录返回脱敏后的用户信息
     */
    @Override
    public User userLogin(String account, String password, HttpServletRequest request) {
        //1.校验
        if(StringUtils.isAnyBlank(account,password)){
            throw new BusinessException(ErrorCode.NULL_ERROR, "账号和密码不能为空");
        }
        //2.密码加密
        String encodedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        //3.查询用户是否存在
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("account", account);
        userQueryWrapper.eq("password", encodedPassword);
        User user = this.getOne(userQueryWrapper);
        if(user==null){
            throw new BusinessException(ErrorCode.PARAM_ERROR, "账号或密码错误");
        }
        //4.记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        return user;
    }

    /**
     * 获取当前用户
     * @param request 前端请求
     * @return 当前用户
     */
    @Override
    public User getCurrentUser(HttpServletRequest request) {
        //获取当前用户
        User sessionUser = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        //用户未登录
        if (sessionUser == null) {
            throw new BusinessException(ErrorCode.NO_LOGIN, "用户处于未登录状态");
        }
        //查询用户信息
        User user = this.getById(sessionUser.getId());
        //用户不存在
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAM_ERROR, "用户不存在");
        }
        return user;
    }

    /**
     * 注销接口
     * @param request 前端请求
     * @return 是否注销成功
     */
    @Override
    public Boolean userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "请求失败");
        }
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }

    /**
     * 用户查询
     * @param username 用户名
     * @return 根据用户名查询到的用户列表
     */
    @Override
    public List<User> userSelect(String username, HttpServletRequest request) {
        //非管理员
        if(isNotAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH, "没有权限查看用户信息");
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("username", username);
        return this.list(userQueryWrapper);
    }

    /**
     * 全部用户
     * @param request 请求
     * @return 列表
     */
    @Override
    public List<User> getAllUser(HttpServletRequest request) {
        //非管理员
        if(isNotAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH, "没有权限查看用户信息");
        }
        return this.list(); //mybatisPlus,IService内置方法 list()
    }

    /**
     * 删除用户
     * @param id 用户id
     * @return 根据用户id删除用户是否成功
     */
    @Override
    public Boolean userDelete(long id, HttpServletRequest request) {
        //非管理员
        if(isNotAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH, "没有权限删除用户信息");
        }
        if(id<=0){
            throw new BusinessException(ErrorCode.PARAM_ERROR, "用户id不合法");
        }
        return this.removeById(id); //mybatisPlus,IService内置方法 removeById()
    }

    /**
     * 验证是否为管理员
     * @param request 用户请求
     * @return 是否为管理员
     */
    @Override
    public Boolean isNotAdmin(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        return user == null || user.getUserRole() != ADMIN_ROLE;
    }



}




