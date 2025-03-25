package com.bytebreeze.usercenterboot.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 */
@TableName(value ="user")
@Data
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     * 默认使用雪花算法结合不含中划线的UUID作为ID生成方式
     * 这里改成数据库自增
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户角色 0-普通用户 1-管理员
     */
    private Integer userRole;

    /**
     * 用户头像路径
     */
    private String avatar;

    /**
     * 用户性别 0女 1男
     */
    private Integer gender;

    /**
     * 用户电话
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户创建时间
     */
    private Date createTime;

    /**
     * 用户更新时间
     */
    private Date updateTime;

    /**
     * 用户当前状态0正常
     */
    private Integer status;

    /**
     * 是否删除(逻辑删除)0正常1已删除
     */
    @TableLogic    //mybatis-plus绑定逻辑删除字段
    private Integer isDelete;
}