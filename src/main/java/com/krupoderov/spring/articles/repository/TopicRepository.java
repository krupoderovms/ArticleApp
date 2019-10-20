package com.krupoderov.spring.articles.repository;

import com.krupoderov.spring.articles.model.Section;
import com.krupoderov.spring.articles.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/** Данный интерфейс неообходим для возможности
 * использования методов
 * получения и управлением данных из
 * базы(напр. findAllById, delete, save и тд)
 *
 * @version 1.1
 * @author Krupoderov Mikhail
 */
@Repository
public interface TopicRepository extends PagingAndSortingRepository<Topic, Long> {
    Page<Topic> findBySection(Section section, Pageable pageable);

    Page<Topic> findByTitleContainingOrTextContaining(String searchWordInTitle, String searchWordInText, Pageable pageable);
}
