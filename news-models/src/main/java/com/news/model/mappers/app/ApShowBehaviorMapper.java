package com.news.model.mappers.app;

import com.news.model.behavior.pojos.ApShowBehavior;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ajie
 * @date 2023/5/14
 * @description:
 */
public interface ApShowBehaviorMapper {
    /**
     * 获取以及存在的用户数据
     * @param entryId
     * @param articleIds
     * @return
     */
    List<ApShowBehavior> selectListByEntryIdAndArticleIds(@Param("entryId") Integer entryId,
                                                          @Param("articleIds") Integer[] articleIds);

    /**
     * 保存用户展现行为数据
     * @param articleIds  文章IDS
     * @param entryId 实体ID
     */
    void saveBehaviors(@Param("entryId") Integer entryId,
                       @Param("articleIds") Integer[] articleIds);
}
