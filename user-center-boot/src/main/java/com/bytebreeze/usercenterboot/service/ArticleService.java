package com.bytebreeze.usercenterboot.service;

import com.bytebreeze.usercenterboot.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.List;

/**
* @author 20629
* @description 针对表【article】的数据库操作Service
* @createDate 2025-03-27 00:26:42
*/
public interface ArticleService extends IService<Article> {

    /**
     * 获取数据库中所有文章列表
     * @return 所有文章列表
     */
    List<Article> articleSelectAll();

    /**
     * 通过文章ID查询文章
     */
    Article articleSelectById(Long id);

    /**
     * 通过作者ID查询文章列表
     */
    List<Article> articleSelectByAuthorId(Long authorId);

    /**
     * 通过文章标题模糊查询
     */
    List<Article> articleSelectByTitle(String title);

    /**
     * 新增文章（写文章）插入数据库
     * @return 文章ID
     */
    Long articleInsert(Article article);

    /**
     * 修改文章
     * @return 文章ID
     */
    Long articleUpdate(Article article);

    /**
     * 删除文章
     * @param id
     * @return
     */
    Boolean articleDelete(Long id);

}
