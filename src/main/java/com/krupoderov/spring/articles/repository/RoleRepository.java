package com.krupoderov.spring.articles.repository;

import com.krupoderov.spring.articles.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByAuthority(String authority);
}
