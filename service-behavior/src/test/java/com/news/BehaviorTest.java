package com.news;

import com.news.behavior.BehaviorApplication;
import com.news.behavior.service.AppShowBehaviorService;
import com.news.model.article.pojos.ApArticle;
import com.news.model.behavior.dtos.ShowBehaviorDto;
import com.news.model.common.dtos.ResponseResult;
import com.news.model.user.pojos.ApUser;
import com.news.utils.threadlocal.AppThreadLocalUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ajie
 * @date 2023/5/14
 * @description:
 */
@SpringBootTest(classes = BehaviorApplication.class)
@RunWith(SpringRunner.class)
public class BehaviorTest {

    @Autowired
    private AppShowBehaviorService showBehaviorService;


    @Test
    public void testSaveBehavior() {
        ApUser apUser = new ApUser();
        apUser.setId(1l);
        AppThreadLocalUtils.setUser(apUser);
        ShowBehaviorDto dto = new ShowBehaviorDto();
        List<ApArticle> articles = new ArrayList<>();
        ApArticle apArticle = new ApArticle();
        apArticle.setId(1);
        articles.add(apArticle);
        dto.setArticleIds(articles);
        showBehaviorService.saveShowBehavior(dto);
        //articleIndexService.saveBehaviors(data);
    }
}
