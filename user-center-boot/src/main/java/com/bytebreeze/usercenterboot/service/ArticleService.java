package com.bytebreeze.usercenterboot.service;

import com.bytebreeze.usercenterboot.entity.Article;
import com.bytebreeze.usercenterboot.entity.response.ArticleDTO;
import com.github.yulichang.base.MPJBaseService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
* @author 20629
* @description 针对表【article】的数据库操作Service
* @createDate 2025-03-27 00:26:42
*/
public interface ArticleService extends MPJBaseService<Article> {

    /**
     * 获取所有"已发布"文章列表
     * @return 所有文章列表
     */
    List<ArticleDTO> articleSelectPublish();

    /**
     * 获取所有"待审核"文章列表（审核员/管理员）
     * @return 所有文章列表
     */
    List<ArticleDTO> articleSelectReview();

    /**
     * 通过文章ID查询文章（阅读全文）
     * @param id 文章ID
     * @return 本文章
     */
    ArticleDTO articleSelectPublishById(Long id);

    /**
     * 别人主页“已发布”文章
     * @param authorId 作者ID
     * @return 文章列表
     */
    List<ArticleDTO> articleSelectPublishByAuthorId(Long authorId);

    /**
     * 通过文章标题模糊查询“已发布”文章
     * @param title 文章标题
     * @return 模糊查询到的文章列表
     */
    List<ArticleDTO> articleSelectPublishByTitle(String title);

    /**
     * 查询我的文章（我的主页）
     * @param request 前端请求
     * @param articleStatus 文章状态
     * @return 文章列表
     */
    List<ArticleDTO> articleSelectMine(HttpServletRequest request, int articleStatus);

    /**
     * 新增文章（写文章）插入数据库
     *
     * @param article 文章
     * @return 文章ID
     */
    Long articleInsert(Article article);

    /**
     * 修改文章
     * @param article 文章
     * @return 是否修改成功
     */
    Boolean articleUpdate(Article article);

    /**
     * 删除文章
     * @param id 文章ID
     * @return 是否删除成功
     */
    Boolean articleDelete(Long id);

    /**
     * 阅读量+1
     *
     * @param id 文章ID
     * @return 阅读量
     */
    Boolean articleViewAdd1ById(Long id);

}
