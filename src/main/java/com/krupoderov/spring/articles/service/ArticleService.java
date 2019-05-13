package com.krupoderov.spring.articles.service;

import com.krupoderov.spring.articles.model.Article;
import com.krupoderov.spring.articles.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository repository;

    public void save(Article article) {
        repository.save(article);
    }

    /* Метод, позволяющий "достать" все объекты Article из базы данных
        и отобразить их в убывающем порядке по дате создания
     */
    public List<Article> getAll() {
        return StreamSupport
                .stream(
                        Spliterators.spliteratorUnknownSize(repository.findAll().iterator(), Spliterator.NONNULL),
                        false)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public void delete(Integer articleId) {
        repository.deleteById(articleId);
    }
}
