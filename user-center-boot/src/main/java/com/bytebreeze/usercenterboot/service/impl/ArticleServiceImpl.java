package com.bytebreeze.usercenterboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bytebreeze.usercenterboot.entity.Article;
import com.bytebreeze.usercenterboot.entity.User;
import com.bytebreeze.usercenterboot.entity.response.ArticleDTO;
import com.bytebreeze.usercenterboot.service.ArticleService;
import com.bytebreeze.usercenterboot.mapper.ArticleMapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bytebreeze.usercenterboot.constant.ArticleConstant.*;
import static com.bytebreeze.usercenterboot.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author 20629
 * @description 针对表【article】的数据库操作Service实现
 * @createDate 2025-03-27 00:26:42
 */
@Service
public class ArticleServiceImpl extends MPJBaseServiceImpl<ArticleMapper, Article>
        implements ArticleService {

    /**
     * 获取所有"已发布"文章列表（所有文章）
     *
     * @return 所有文章列表
     */
    @Override
    public List<ArticleDTO> articleSelectPublish() {
        MPJQueryWrapper<Article> articleMPJQueryWrapper = new MPJQueryWrapper<Article>()
                .select("t.*, u.username as authorName, u.avatar as authorAvatar")
                .eq("t.status", PUBLISH_ARTICLE) //已发布的文章
                .leftJoin("user u ON t.author_id=u.id AND u.is_delete=0")
                .orderByDesc("t.create_time");
        return this.selectJoinList(ArticleDTO.class, articleMPJQueryWrapper);
    }

    /**
     * 获取所有"待审核"文章列表（文章审核员/管理员）
     *
     * @return 所有文章列表
     */
    @Override
    public List<ArticleDTO> articleSelectReview() {
        MPJQueryWrapper<Article> articleMPJQueryWrapper = new MPJQueryWrapper<Article>()
                .select("t.*, u.username as authorName, u.avatar as authorAvatar")
                .eq("t.status", REVIEW_ARTICLE) //待审核的文章
                .leftJoin("user u ON t.author_id=u.id AND u.is_delete=0")
                .orderByDesc("t.create_time");
        return this.selectJoinList(ArticleDTO.class, articleMPJQueryWrapper);
    }

    /**
     * 通过文章ID查询文章（读文章）
     */
    @Override
    public ArticleDTO articleSelectPublishById(Long id) {
        MPJQueryWrapper<Article> articleMPJQueryWrapper = new MPJQueryWrapper<Article>()
                .select("t.*, u.username as authorName, u.avatar as authorAvatar")
                .eq("t.id", id)
                .leftJoin("user u ON t.author_id=u.id AND u.is_delete=0");
        return this.selectJoinOne(ArticleDTO.class, articleMPJQueryWrapper);
    }

    /**
     * 通过作者ID查询“已发布”文章列表（别人主页）
     */
    @Override
    public List<ArticleDTO> articleSelectPublishByAuthorId(Long authorId) {
        MPJQueryWrapper<Article> articleMPJQueryWrapper = new MPJQueryWrapper<Article>()
                .select("t.*, u.username as authorName, u.avatar as authorAvatar")
                .eq("t.status", PUBLISH_ARTICLE) //已发布的文章
                .eq("t.author_id", authorId)
                .leftJoin("user u ON t.author_id=u.id AND u.is_delete=0")
                .orderByDesc("t.create_time");
        return this.selectJoinList(ArticleDTO.class, articleMPJQueryWrapper);
    }

    /**
     * 通过文章标题模糊查询“已发布”文章
     */
    @Override
    public List<ArticleDTO> articleSelectPublishByTitle(String title) {
        MPJQueryWrapper<Article> articleMPJQueryWrapper = new MPJQueryWrapper<Article>()
                .select("t.*, u.username as authorName, u.avatar as authorAvatar")
                .eq("t.status", PUBLISH_ARTICLE) //已发布的文章
                .like("t.title", title)
                .leftJoin("user u ON t.author_id=u.id AND u.is_delete=0")
                .orderByDesc("t.create_time");
        return this.selectJoinList(ArticleDTO.class, articleMPJQueryWrapper);
    }

    /**
     * 查询我的文章（我的主页）
     *
     * @param request       前端请求
     * @param articleStatus 文章状态
     * @return 文章列表
     */
    @Override
    public List<ArticleDTO> articleSelectMine(HttpServletRequest request, int articleStatus) {
        User sessionUser = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        Long userId = sessionUser.getId();
        MPJQueryWrapper<Article> articleMPJQueryWrapper = new MPJQueryWrapper<Article>()
                .select("t.*, u.username as authorName, u.avatar as authorAvatar")
                .eq("t.status", articleStatus) //草稿文章
                .eq("t.author_id", userId)
                .leftJoin("user u ON t.author_id=u.id AND u.is_delete=0")
                .orderByDesc("t.create_time");
        return this.selectJoinList(ArticleDTO.class, articleMPJQueryWrapper);
    }

    /**
     * 新增文章（写文章）插入数据库
     *
     * @return 是否插入成功
     */
    @Override
    public Long articleInsert(Article article) {
         return this.save(article) ? article.getId() : null;
    }

    /**
     * 修改文章
     *
     * @return 文章ID
     */
    public Boolean articleUpdate(Article article) {
        return this.updateById(article);
    }

    /**
     * 删除文章
     *
     * @param id 文章ID
     */
    @Override
    public Boolean articleDelete(Long id) {
        return this.removeById(id);
    }

    /**
     * 阅读量+1
     *
     * @param id 文章ID
     * @return 是否更新成功
     */
    @Override
    public Boolean articleViewAdd1ById(Long id) {
        Article article = this.getById(id);
        UpdateWrapper<Article> updateWrapper = new UpdateWrapper<Article>()
                .set("view_count", article.getViewCount() + 1);
        return this.update(updateWrapper);

    }
}




