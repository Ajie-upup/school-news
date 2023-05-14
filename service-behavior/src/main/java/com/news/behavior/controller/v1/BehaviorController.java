package com.news.behavior.controller.v1;

import com.news.apis.BehaviorControllerApi;
import com.news.behavior.service.AppShowBehaviorService;
import com.news.model.behavior.dtos.ShowBehaviorDto;
import com.news.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ajie
 * @date 2023/5/14
 * @description:
 */
@RestController
@RequestMapping("/api/v1/behavior")
public class BehaviorController implements BehaviorControllerApi {

    @Autowired
    private AppShowBehaviorService appShowBehaviorService;

    @Override
    @PostMapping("/save_behavior")
    public ResponseResult saveShowBehavior(@RequestBody ShowBehaviorDto dto) {
        return appShowBehaviorService.saveShowBehavior(dto);
    }
}
