package com.news.model.mappers.app;

import com.news.model.behavior.pojos.ApBehaviorEntry;
import org.apache.ibatis.annotations.Param;

/**
 * @author ajie
 * @date 2023/5/14
 * @description:
 */
public interface ApBehaviorEntryMapper {

    ApBehaviorEntry selectByUserIdOrEquipment(@Param("userId") Long userId,
                                              @Param("equipmentId") Integer equipmentId);

}
