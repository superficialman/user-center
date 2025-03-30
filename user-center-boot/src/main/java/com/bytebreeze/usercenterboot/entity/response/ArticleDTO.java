package com.bytebreeze.usercenterboot.entity.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author LQ
 * @date 2025/3/27
 * @description 自定义返回的字段: 包含作者头像和用户名
 */
@Data
public class ArticleDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String content;
    private Long authorId;

    private String authorName; //作者名
    private String authorAvatar; //作者头像

    private Integer status;
    private Integer viewCount;
    private Date createTime;


}
