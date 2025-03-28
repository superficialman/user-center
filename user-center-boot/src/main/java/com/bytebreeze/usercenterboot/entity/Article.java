package com.bytebreeze.usercenterboot.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@TableName(value ="article")
@Data
public class Article implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 文章ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章正文
     */
    private String content;

    /**
     * 作者ID
     */
    private Long authorId;

    /**
     * 文章状态（0-草稿，1-审核中，2-已发布）
     */
    private Integer status;

    /**
     * 阅读量
     */
    private Integer viewCount;

    /**
     * 逻辑删除（0-正常，1-已删除）
     */
    @TableLogic
    private Integer idDelete;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}