package com.bytebreeze.usercenterboot.service;

import com.bytebreeze.usercenterboot.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bytebreeze.usercenterboot.entity.response.ArticleDTO;

import java.io.Serializable;
import java.util.List;

/**
* @author 20629
* @description 针对表【article】的数据库操作Service
* @createDate 2025-03-27 00:26:42
*/
public interface ArticleService extends IService<Article> {

    /**
     * 获取所有"已发布"文章列表
     * @return 所有文章列表
     */
    List<ArticleDTO> articleSelectPublish();

    /**
     * 获取所有"待审核"文章列表
     * @return 所有文章列表
     */
    List<ArticleDTO> articleSelectReview();

    /**
     * 通过文章ID查询文章
     */
    ArticleDTO articleSelectPublishById(Long id);

    /**
     * 通过作者ID查询“已发布”文章列表
     */
    List<ArticleDTO> articleSelectPublishByAuthorId(Long authorId);

    /**
     * 通过作者ID查询“待审核”文章列表
     */
    List<ArticleDTO> articleSelectReviewByAuthorId(Long authorId);

    /**
     * 通过作者ID查询“草稿”文章列表
     */
    List<ArticleDTO> articleSelectDraftByAuthorId(Long authorId);

    /**
     * 通过文章标题模糊查询“已发布”文章
     */
    List<ArticleDTO> articleSelectPublishByTitle(String title);

    /**
     * 新增文章（写文章）插入数据库
     * @return 文章ID
     */
    Long articleInsert(Article article);

    /**
     * 修改文章
     * @return 文章ID
     */
    Long articleUpdate(Long id);

    /**
     * 删除文章
     */
    Boolean articleDelete(Long id);

    /**
     * 阅读量+1
     * @return 阅读量
     */
    Integer articleViewAdd1ById(Long id);

}
