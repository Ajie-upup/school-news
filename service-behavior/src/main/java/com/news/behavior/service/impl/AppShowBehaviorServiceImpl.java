package com.news.behavior.service.impl;

import com.news.behavior.service.AppShowBehaviorService;
import com.news.model.behavior.dtos.ShowBehaviorDto;
import com.news.model.behavior.pojos.ApBehaviorEntry;
import com.news.model.behavior.pojos.ApShowBehavior;
import com.news.model.common.dtos.ResponseResult;
import com.news.model.common.enums.AppHttpCodeEnum;
import com.news.model.mappers.app.ApBehaviorEntryMapper;
import com.news.model.mappers.app.ApShowBehaviorMapper;
import com.news.model.user.pojos.ApUser;
import com.news.utils.threadlocal.AppThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ajie
 * @date 2023/5/14
 * @description:
 */
@Service
public class AppShowBehaviorServiceImpl implements AppShowBehaviorService {

    @Autowired
    private ApShowBehaviorMapper apShowBehaviorMapper;
    @Autowired
    private ApBehaviorEntryMapper apBehaviorEntryMapper;

    @Override
    public ResponseResult saveShowBehavior(ShowBehaviorDto dto) {
        // 获取用户信息
        ApUser user = AppThreadLocalUtils.getUser();
        // 用户和设备不能同时为空
        if (user == null && dto.getEquipmentId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
//        if (user == null && (dto.getArticleIds() == null || dto.getArticleIds().isEmpty())) {
//            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
//        }
        Long userId = null;
        if (user != null) {
            userId = user.getId();
        }
        ApBehaviorEntry apBehaviorEntry = apBehaviorEntryMapper.selectByUserIdOrEquipment(userId, dto.getEquipmentId());
        // 行为实体找以及注册了，逻辑上这里是必定有值得，除非参数错误
        if (apBehaviorEntry == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 过滤新数据
        // 集合转数组传递给下一个 mapper
        Integer[] articleIds = new Integer[dto.getArticleIds().size()];
        for (int i = 0; i < articleIds.length; i++) {
            articleIds[i] = dto.getArticleIds().get(i).getId();
        }
        // 根据行为实体和文章id 查询 app 行为表
        List<ApShowBehavior> apShowBehaviors = apShowBehaviorMapper.selectListByEntryIdAndArticleIds(apBehaviorEntry.getId(), articleIds);

        // 数据过滤，删除已经存在的文章 id
        List<Integer> articleIdsList = new ArrayList<>(Arrays.asList(articleIds));
        if (!apShowBehaviors.isEmpty()) {
            apShowBehaviors.forEach(item -> {
                articleIdsList.remove(item.getArticleId());
            });
        }
        // 插入新数据
        if (!articleIdsList.isEmpty()) {
            articleIds = new Integer[articleIdsList.size()];
            articleIdsList.toArray(articleIds);
            apShowBehaviorMapper.saveBehaviors(apBehaviorEntry.getId(), articleIds);
        }
        return ResponseResult.okResult(0);
    }
}
