package com.bytebreeze.usercenterboot.service;

import com.bytebreeze.usercenterboot.entity.Article;
import com.bytebreeze.usercenterboot.entity.User;
import com.bytebreeze.usercenterboot.entity.response.ArticleDTO;
import com.github.yulichang.query.MPJQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.bytebreeze.usercenterboot.constant.ArticleConstant.PUBLISH_ARTICLE;

@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    void TestArticleSelectPublish() {
        MPJQueryWrapper<Article> articleMPJQueryWrapper = new MPJQueryWrapper<Article>()
                .select("t.*, u.username as authorName, u.avatar as authorAvatar")
                .eq("t.status", PUBLISH_ARTICLE)
                .leftJoin("user u ON t.author_id=u.id AND u.is_delete=0");
        List<ArticleDTO> articleDTOList = articleService.selectJoinList(ArticleDTO.class, articleMPJQueryWrapper);
        articleDTOList.forEach(articleDTO -> {
            System.out.println(articleDTO.getId()+articleDTO.getAuthorName());
        });
    }
}