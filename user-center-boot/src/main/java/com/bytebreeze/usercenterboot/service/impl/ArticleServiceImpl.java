package com.bytebreeze.usercenterboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytebreeze.usercenterboot.entity.Article;
import com.bytebreeze.usercenterboot.service.ArticleService;
import com.bytebreeze.usercenterboot.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author 20629
* @description 针对表【article】的数据库操作Service实现
* @createDate 2025-03-27 00:26:42
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

}




