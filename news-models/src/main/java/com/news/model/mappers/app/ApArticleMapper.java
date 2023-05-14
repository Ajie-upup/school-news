package com.news.model.mappers.app;



import com.news.model.article.dtos.ArticleHomeDto;
import com.news.model.article.pojos.ApArticle;
import com.news.model.article.pojos.ApArticleSDto;
import com.news.model.user.pojos.ApUserArticleList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApArticleMapper {


    /**
     * 通过ID查询文章
     */
    ApArticle selectById(Long id);

    /**
     * 照用户地理位置，加载文章
     */
    List<ApArticle> loadArticleListByLocation(@Param("dto") ArticleHomeDto dto, @Param("type") short type);

    /**
     * 依据文章IDS来获取文章详细内容
     */
    List<ApArticle> loadArticleListByIdList(@Param("list") List<ApUserArticleList> list);


    /**
     * 依据文章IDS来获取文章详细内容
     */
    List<ApArticle> loadArticleListByIdListV2(List<Integer> list);


    void insert(ApArticle apArticle);

    List<Integer> findByAuthorId(Integer apAuthorId);


    /**
     * 抽取最近的文章数据用于计算热文章
     */
    List<ApArticle> loadLastArticleForHot(String lastDate);

    /**
     * 查询
     */
    List<ApArticle> selectList(ApArticle apArticle);

    /**
     * 更新
     */
    void updateSyncStatus(ApArticle apArticle);

    /**
     * 获取当日发布的图文
     */
    List<ApArticleSDto> selectListForStatistic();

}