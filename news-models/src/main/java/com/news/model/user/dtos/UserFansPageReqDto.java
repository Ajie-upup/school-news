package com.news.model.user.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;

@Data
public class UserFansPageReqDto extends PageRequestDto {
    private String fansName;
    private Short level;
}
