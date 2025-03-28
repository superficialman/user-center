package com.bytebreeze.usercenterboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytebreeze.usercenterboot.entity.Article;
import com.bytebreeze.usercenterboot.entity.User;
import com.bytebreeze.usercenterboot.entity.response.ArticleDTO;
import com.bytebreeze.usercenterboot.mapper.UserMapper;
import com.bytebreeze.usercenterboot.service.ArticleService;
import com.bytebreeze.usercenterboot.mapper.ArticleMapper;
import com.bytebreeze.usercenterboot.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author 20629
* @description 针对表【article】的数据库操作Service实现
* @createDate 2025-03-27 00:26:42
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private UserService userService;

    private ArticleDTO getArticleDTO(Article article){
        if(article == null){
            return null;
        }
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        articleDTO.setAuthorId(article.getAuthorId());
        articleDTO.setStatus(article.getStatus());
        articleDTO.setViewCount(article.getViewCount());
        articleDTO.setCreateTime(article.getCreateTime());

        User user = userService.getById(article.getAuthorId());
        if(user != null){
            articleDTO.setAuthorName(user.getUsername());
            articleDTO.setAuthorAvatar(user.getAvatar());
        }
        return articleDTO;
    }

    /**
     * 获取所有"已发布"文章列表
     * @return 所有文章列表
     */
    @Override
    public List<ArticleDTO> articleSelectPublish() {
        ArrayList<ArticleDTO> articleDTOS = new ArrayList<>();
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("status", 2); //已发布文章
        List<Article> articleList = articleMapper.selectList(articleQueryWrapper);
        if(articleList.isEmpty()){
            //TODO
            return articleDTOS;
        }
        for (Article article : articleList) {
            ArticleDTO articleDTO = getArticleDTO(article);
            articleDTOS.add(articleDTO);
        }
        return articleDTOS;
    }

    /**
     * 获取所有"待审核"文章列表
     *
     * @return 所有文章列表
     */
    @Override
    public List<ArticleDTO> articleSelectReview() {
        return List.of();
    }

    /**
     * 通过文章ID查询文章
     */
    @Override
    public ArticleDTO articleSelectPublishById(Long id) {
        return null;
    }

    /**
     * 通过作者ID查询“已发布”文章列表
     */
    @Override
    public List<ArticleDTO> articleSelectPublishByAuthorId(Long authorId) {
        return List.of();
    }

    /**
     * 通过作者ID查询“待审核”文章列表
     */
    @Override
    public List<ArticleDTO> articleSelectReviewByAuthorId(Long authorId) {
        return List.of();
    }

    /**
     * 通过作者ID查询“草稿”文章列表
     */
    @Override
    public List<ArticleDTO> articleSelectDraftByAuthorId(Long authorId) {
        return List.of();
    }

    /**
     * 通过文章标题模糊查询“已发布”文章
     */
    @Override
    public List<ArticleDTO> articleSelectPublishByTitle(String title) {
        return List.of();
    }

    /**
     * 新增文章（写文章）插入数据库
     * @return 文章ID
     */
    @Override
    public Long articleInsert(Article article) {
        return 0L;
    }

    /**
     * 修改文章
     * @return 文章ID
     */
    @Override
    public Long articleUpdate(Long id) {
        return 0L;
    }

    /**
     * 删除文章
     * @param id 文章ID
     */
    @Override
    public Boolean articleDelete(Long id) {
        return null;
    }

    /**
     * 阅读量+1
     *
     * @param id 文章ID
     * @return 阅读量
     */
    @Override
    public Integer articleViewAdd1ById(Long id) {
        return 0;
    }
}




