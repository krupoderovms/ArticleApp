package com.krupoderov.spring.articles.service;

import com.krupoderov.spring.articles.model.Article;
import com.krupoderov.spring.articles.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository repository;

    public void save(Article article) {
        repository.save(article);
    }
}
