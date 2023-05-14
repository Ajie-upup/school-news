package com.news.article.service.impl;

import com.news.article.ArticleConstants;
import com.news.article.service.AppArticleService;
import com.news.model.article.dtos.ArticleHomeDto;
import com.news.model.article.pojos.ApArticle;
import com.news.model.common.dtos.ResponseResult;
import com.news.model.mappers.app.ApArticleMapper;
import com.news.model.mappers.app.ApUserArticleListMapper;
import com.news.model.user.pojos.ApUser;
import com.news.model.user.pojos.ApUserArticleList;
import com.news.utils.threadlocal.AppThreadLocalUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ajie
 * @date 2023/5/13
 * @description:
 */
@Service
public class AppArticleServiceImpl implements AppArticleService {
    // 定义相关分页常量
    private final static Integer DEFAULT_PAGE_SIZE = 20;
    private final static Integer MAX_PAGE_SIZE = 50;

    @Autowired
    private ApArticleMapper apArticleMapper;
    @Autowired
    private ApUserArticleListMapper apUserArticleListMapper;

    @Override
    public ResponseResult load(Short type, ArticleHomeDto dto) {
        // 获取当前登录的用户
        ApUser user = AppThreadLocalUtils.getUser();

        // 1.相关参数校验
        Integer size = dto.getSize();
        String tag = dto.getTag();
        // 1.1 分页参数校验
        if (size == null || size <= 0) {
            // 当输入分页参数不合法时，传入默认参数
            size = DEFAULT_PAGE_SIZE;
        }
        // 当输入分页参数超出范围时，赋值最大参数
        size = Math.min(size, MAX_PAGE_SIZE);
        dto.setSize(size);

        // 1.2 类型参数校验
        if (!type.equals(ArticleConstants.TYPE_LOAD_MORE) && !type.equals(ArticleConstants.TYPE_LOAD_NEW)) {
            // 参数中的类型不合法时，默认查询更多
            type = ArticleConstants.TYPE_LOAD_MORE;
        }
        // 1.3 文章频道参数校验
        if (StringUtils.isEmpty(tag)) {
            dto.setTag(ArticleConstants.DEFAULT_TAG);
        }
        // 时间处理
        if (dto.getMaxBehotTime() == null) {
            dto.setMaxBehotTime(new Date());
        }
        if (dto.getMinBehotTime() == null) {
            dto.setMinBehotTime(new Date());
        }
        List<ApArticle> apArticleList;
        if (user == null) {
            // 用户未登录时，默认查询全部文章列表
            apArticleList = getDefaultArticle(dto, type);

        } else {
            // 用户登录后，先查询相关推荐列表，如果没有查询全部文章列表
            apArticleList = getUserArticle(user, dto, type);
        }

        return ResponseResult.okResult(apArticleList);
    }

    /**
     * 从默认的大文章列表中获取文章
     */
    private List<ApArticle> getDefaultArticle(ArticleHomeDto dto, Short type) {
        return apArticleMapper.loadArticleListByLocation(dto, type);
    }

    /**
     * 先从用户的推荐表中查找文章，如果没有再从大文章列表中获取
     */
    private List<ApArticle> getUserArticle(ApUser user, ArticleHomeDto dto, Short type) {
        List<ApUserArticleList> list = apUserArticleListMapper.loadArticleIdListByUser(user, dto, type);
        List<ApArticle> apArticleList;
        if (!list.isEmpty()) {
            apArticleList = apArticleMapper.loadArticleListByIdList(list);
        } else {
            apArticleList = getDefaultArticle(dto, type);
        }
        return apArticleList;
    }
}
