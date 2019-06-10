package com.krupoderov.spring.articles.repo;

import com.krupoderov.spring.articles.model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** Данный интерфейс неообходим для возможности
 * использования методов
 * получения и управлением данных из
 * базы(напр. findAllById, delete, save и тд)
 */
@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
}
