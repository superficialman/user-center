package com.bytebreeze.usercenterboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytebreeze.usercenterboot.entity.Article;
import com.bytebreeze.usercenterboot.service.ArticleService;
import com.bytebreeze.usercenterboot.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 20629
* @description 针对表【article】的数据库操作Service实现
* @createDate 2025-03-27 00:26:42
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

    /**
     * 获取数据库中所有文章列表
     * @return 所有文章列表
     */
    @Override
    public List<Article> articleSelectAll() {
        return List.of();
    }

    /**
     * 通过文章ID查询文章
     * @param id
     */
    @Override
    public Article articleSelectById(Long id) {
        return null;
    }

    /**
     * 通过作者ID查询文章列表
     * @param authorId
     */
    @Override
    public List<Article> articleSelectByAuthorId(Long authorId) {
        return List.of();
    }

    /**
     * 通过文章标题模糊查询
     * @param title
     */
    @Override
    public List<Article> articleSelectByTitle(String title) {
        return List.of();
    }

    /**
     * 新增文章（写文章）插入数据库
     * @param article
     * @return 文章ID
     */
    @Override
    public Long articleInsert(Article article) {
        return 0L;
    }

    /**
     * 修改文章
     * @param article
     * @return 文章ID
     */
    @Override
    public Long articleUpdate(Article article) {
        return 0L;
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    @Override
    public Boolean articleDelete(Long id) {
        return null;
    }
}




