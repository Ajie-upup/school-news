package com.news.article.service;

import com.news.model.article.dtos.ArticleHomeDto;
import com.news.model.common.dtos.ResponseResult;

/**
 * @author ajie
 * @date 2023/5/13
 * @description:
 */
public interface AppArticleService {

    /**
     * 加载文章信息
     * 1 - 加载更多   2 - 加载最新
     */
    ResponseResult load(Short type, ArticleHomeDto dto);

}
