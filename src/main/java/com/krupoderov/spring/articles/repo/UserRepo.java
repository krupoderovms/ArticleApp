package com.krupoderov.spring.articles.repo;

import com.krupoderov.spring.articles.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
