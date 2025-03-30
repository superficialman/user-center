package com.bytebreeze.usercenterboot.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.bytebreeze.usercenterboot.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bytebreeze.usercenterboot.entity.response.ArticleDTO;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 20629
* @description 针对表【article】的数据库操作Mapper
* @createDate 2025-03-27 00:26:42
* @Entity com.bytebreeze.usercenterboot.entity.Article
*/
public interface ArticleMapper extends MPJBaseMapper<Article> {

}




