package com.news.model.behavior.pojos;


import com.news.model.annotation.IdEncrypt;
import lombok.Data;

import java.util.Date;

@Data
public class ApShareBehavior {
    private Long id;
    @IdEncrypt
    private Integer entryId;
    @IdEncrypt
    private Integer articleId;
    private Short type;
    private Date createdTime;
}