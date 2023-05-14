package com.news.model.media.dtos;

import lombok.Data;
import com.news.model.common.dtos.PageRequestDto;

@Data
public class WmMaterialListDto extends PageRequestDto {
    Short isCollected; //1 查询收藏的
}
