package com.krupoderov.spring.articles.repository;

import com.krupoderov.spring.articles.model.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<Photo, Long> {
    Photo findByUserId(Long id);
}
