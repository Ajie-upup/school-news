package com.news.apis;

import com.news.model.article.dtos.ArticleHomeDto;
import com.news.model.common.dtos.ResponseResult;

/**
 * @author ajie
 * @date 2023/5/13
 * @description:
 */
public interface ArticleHomeControllerApi {

    /**
     * 加载首页文章
     */
    ResponseResult load(ArticleHomeDto articleHomeDto);


    /**
     * 加载更多
     */
    ResponseResult loadMore(ArticleHomeDto articleHomeDto);

    /**
     * 上拉刷新文章列表
     */
    ResponseResult loadNew(ArticleHomeDto articleHomeDto);
}
