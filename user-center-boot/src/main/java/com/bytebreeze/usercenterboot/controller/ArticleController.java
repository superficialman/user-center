package com.bytebreeze.usercenterboot.controller;

import com.bytebreeze.usercenterboot.common.BaseResponse;
import com.bytebreeze.usercenterboot.entity.Article;
import com.bytebreeze.usercenterboot.entity.response.ArticleDTO;
import com.bytebreeze.usercenterboot.service.ArticleService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.bytebreeze.usercenterboot.constant.ArticleConstant.*;

/**
 * @author makabaka
 * @date 2025/3/28
 * @description 文章请求控制层接口
 * 全部以通用返回类BaseResponse返回
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/article")
public class ArticleController {
    /**
     * 用户服务层接口
     */
    @Resource
    private ArticleService articleService;

    /**
     * 获取所有"已发布"文章列表（所有文章）
     * @return 所有文章列表
     */
    @GetMapping("/allPublish")
    public BaseResponse<List<ArticleDTO>> articleSelectPublish() {
        List<ArticleDTO> articleDTOList = articleService.articleSelectPublish();
        return BaseResponse.success(articleDTOList);
    }

    /**
     * 获取所有"待审核"文章列表（文章审核员/管理员）
     *
     * @return 所有文章列表
     */
    @GetMapping("/allReview")
    public BaseResponse<List<ArticleDTO>>  articleSelectReview() {
        List<ArticleDTO> articleDTOList = articleService.articleSelectReview();
        return BaseResponse.success(articleDTOList);
    }

    /**
     * 通过文章ID查询文章（读文章）
     */
    @GetMapping("/{id}")
    public BaseResponse<ArticleDTO> articleSelectPublishById(@PathVariable Long id) {
        ArticleDTO articleDTO = articleService.articleSelectPublishById(id);
        return BaseResponse.success(articleDTO);
    }

    /**
     * 通过作者ID查询“已发布”文章列表（别人主页）
     */
    @GetMapping("/author/{authorId}")
    public BaseResponse<List<ArticleDTO>> articleSelectPublishByAuthorId(@PathVariable Long authorId) {
        List<ArticleDTO> articleDTOList = articleService.articleSelectPublishByAuthorId(authorId);
        return BaseResponse.success(articleDTOList);
    }

    /**
     * 通过文章标题模糊查询“已发布”文章
     */
    @GetMapping("/search")
    public BaseResponse<List<ArticleDTO>> articleSelectPublishByTitle(@RequestParam String title) {
        List<ArticleDTO> articleDTOList = articleService.articleSelectPublishByTitle(title);
        return BaseResponse.success(articleDTOList);
    }

    /**
     * 查询我的"已发布"文章（我的主页）
     * @param request 前端请求
     * @return 文章列表
     */
    @GetMapping("/mine/publish")
    public BaseResponse<List<ArticleDTO>> articleSelectMyPublish(HttpServletRequest request) {
        List<ArticleDTO> articleDTOList = articleService.articleSelectMine(request, PUBLISH_ARTICLE);
        return BaseResponse.success(articleDTOList);
    }

    /**
     * 查询我的"审核中"文章（我的主页）
     * @param request 前端请求
     * @return 文章列表
     */
    @GetMapping("/mine/review")
    public BaseResponse<List<ArticleDTO>> articleSelectMyReview(HttpServletRequest request) {
        List<ArticleDTO> articleDTOList = articleService.articleSelectMine(request, REVIEW_ARTICLE);
        return BaseResponse.success(articleDTOList);
    }

    /**
     * 查询我的"草稿"文章（我的主页）
     * @param request 前端请求
     * @return 文章列表
     */
    @GetMapping("/mine/draft")
    public BaseResponse<List<ArticleDTO>> articleSelectMyDraft(HttpServletRequest request) {
        List<ArticleDTO> articleDTOList = articleService.articleSelectMine(request, DRAFT_ARTICLE);
        return BaseResponse.success(articleDTOList);
    }

    /**
     * 新增文章（写文章）插入数据库
     * @return 是否插入成功
     */
    @PostMapping("/add")
    public BaseResponse<Long> articleInsert(@RequestBody Article article) {
        Long id = articleService.articleInsert(article);
        return BaseResponse.success(id);
    }

    /**
     * 修改文章
     *
     * @return 文章ID
     */
    @PostMapping("update")
    public BaseResponse<Boolean> articleUpdate(Article article) {
        boolean result = articleService.updateById(article);
        return BaseResponse.success(result);
    }

    /**
     * 删除文章
     * @param id 文章ID
     */
    @PostMapping("/delete/{id}")
    public BaseResponse<Boolean> articleDelete(@PathVariable Long id) {
        boolean result = articleService.articleDelete(id);
        return BaseResponse.success(result);
    }

    /**
     * 阅读量+1
     * @param id 文章ID
     * @return 是否更新成功
     */
    @PostMapping("/{id}/view-count")
    public BaseResponse<Boolean> articleViewAdd1ById(@PathVariable Long id) {
        Boolean result = articleService.articleViewAdd1ById(id);
        return BaseResponse.success(result);
    }
}
