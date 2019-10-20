package com.krupoderov.spring.articles.repository;

import com.krupoderov.spring.articles.model.Reply;
import com.krupoderov.spring.articles.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReplyRepository extends PagingAndSortingRepository<Reply, Long> {
    Page<Reply> findByTopic(Topic topic, Pageable pageable);

    Page<Reply> findByTextContaining(String searchWord, Pageable pageable);
}
