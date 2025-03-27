package com.bytebreeze.usercenterboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName article
 */
@TableName(value ="article")
@Data
public class Article {
    /**
     * 文章ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文章标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 文章正文
     */
    @TableField(value = "content")
    private String content;

    /**
     * 作者ID
     */
    @TableField(value = "author_id")
    private Long authorId;

    /**
     * 文章状态（0-草稿，1-审核中，2-已发布）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 阅读量
     */
    @TableField(value = "view_count")
    private Integer viewCount;

    /**
     * 逻辑删除（0-正常，1-已删除）
     */
    @TableField(value = "id_delete")
    private Integer idDelete;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}