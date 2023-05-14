package com.news.behavior.service;

import com.news.model.behavior.dtos.ShowBehaviorDto;
import com.news.model.common.dtos.ResponseResult;

/**
 * @author ajie
 * @date 2023/5/14
 * @description:
 */
public interface AppShowBehaviorService {

    /**
     * 保存用户点击的行为
     */
    ResponseResult saveShowBehavior(ShowBehaviorDto dto);
}
