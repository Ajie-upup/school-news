package com.news.article;

import com.news.article.service.AppArticleService;
import com.news.model.article.pojos.ApArticle;
import com.news.model.common.dtos.ResponseResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author ajie
 * @date 2023/5/14
 * @description:
 */
@SpringBootTest(classes = ArticleApplication.class)
@RunWith(SpringRunner.class)
public class ArticleTest {

    @Autowired
    private AppArticleService appArticleService;

    @Test
    public void testLoad() {
        ResponseResult result = appArticleService.load(ArticleConstants.TYPE_LOAD_MORE, null);
        List<ApArticle> data = (List<ApArticle>) result.getData();
        for (ApArticle apArticle : data){
            System.out.println(apArticle);
        }
    }
}
