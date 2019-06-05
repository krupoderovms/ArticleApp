package com.krupoderov.spring.articles.repo;

import com.krupoderov.spring.articles.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/** Данный интерфейс неообходим для возможности
 * использования методов
 * получения и управлением данных из
 * базы(напр. findAllById, delete, save и тд)
 */
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
