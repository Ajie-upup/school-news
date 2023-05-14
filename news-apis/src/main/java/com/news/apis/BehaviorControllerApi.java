package com.news.apis;

import com.news.model.behavior.dtos.ShowBehaviorDto;
import com.news.model.common.dtos.ResponseResult;

/**
 * @author ajie
 * @date 2023/5/14
 * @description:
 */
public interface BehaviorControllerApi {

    /**
     * 保存用户点击的行为
     */
    ResponseResult saveShowBehavior(ShowBehaviorDto dto);
}
