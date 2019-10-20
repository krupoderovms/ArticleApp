package com.krupoderov.spring.articles.repository;

import com.krupoderov.spring.articles.model.Section;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Set;

public interface SectionRepository extends PagingAndSortingRepository<Section, Long> {
    Set<Section> findAllByParent(Section parent);

    Page<Section> findByTitleContainingOrTextContaining(String searchWordInTitle, String searhWordInText, Pageable pageable);
}
