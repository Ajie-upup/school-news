package com.news.article.controller.v1;

import com.news.apis.ArticleHomeControllerApi;
import com.news.article.ArticleConstants;
import com.news.article.service.AppArticleService;
import com.news.model.article.dtos.ArticleHomeDto;
import com.news.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ajie
 * @date 2023/5/13
 * @description:
 */
@RestController
@RequestMapping("/api/v1/article")
public class ArticleHomeController implements ArticleHomeControllerApi {

    @Autowired
    private AppArticleService appArticleService;

    @Override
    @GetMapping("/load")
    public ResponseResult load(ArticleHomeDto dto) {
        return appArticleService.load(ArticleConstants.TYPE_LOAD_MORE, dto);
    }

    @Override
    @GetMapping("/loadmore")
    public ResponseResult loadMore(ArticleHomeDto dto) {
        return appArticleService.load(ArticleConstants.TYPE_LOAD_MORE, dto);
    }

    @Override
    @GetMapping("/loadnew")
    public ResponseResult loadNew(ArticleHomeDto dto) {
        return appArticleService.load(ArticleConstants.TYPE_LOAD_NEW, dto);
    }
}
