package com.tensquare.article.service;

import com.tensquare.article.dao.ArticleDao;
import com.tensquare.article.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 文章审核
     *
     * @param id
     */

    public void examine(String id) {
        articleDao.examine(id);
    }

    /**
     * 点赞
     *
     * @param id 文章ID
     * @return
     */
    @Transactional
    public int updateThumbup(String id) {
        return articleDao.updateThumbup(id);
    }

    public Article findById(String id) {

        //从缓存中提取
        Article article = (Article) redisTemplate.opsForValue().get("article_" + id);
        if (article == null) {
            article = articleDao.findById(id).get();
            redisTemplate.opsForValue().set("article_" + id, article);
        }

        return article;
    }


    /**
     * 修改
     *
     * @param article
     */
    public void update(Article article) {
        redisTemplate.delete("article_" + article.getId());//删除缓存
        articleDao.save(article);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        redisTemplate.delete("article_" + id);//删除缓存
        articleDao.deleteById(id);
    }

}
