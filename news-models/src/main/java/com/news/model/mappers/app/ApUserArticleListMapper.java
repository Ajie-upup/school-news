package com.news.model.mappers.app;

import com.news.model.article.dtos.ArticleHomeDto;
import com.news.model.user.pojos.ApUser;
import com.news.model.user.pojos.ApUserArticleList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ajie
 * @date 2023/5/14
 * @description:
 */
public interface ApUserArticleListMapper {

    /**
     * 按照用户属性阅读习惯，加载文章id
     */
    List<ApUserArticleList> loadArticleIdListByUser(@Param("user") ApUser user,
                                                    @Param("dto") ArticleHomeDto dto,
                                                    @Param("type") short type);


}
