package com.krupoderov.spring.articles.repository;

import com.krupoderov.spring.articles.model.User;
import org.springframework.data.repository.CrudRepository;

/** Данный интерфейс неообходим для возможности
 * использования методов
 * получения и управлением данных из
 * базы(напр. findAllById, delete, save и тд)
 *
 * @version 1.0
 * @author Krupoderov Mikhail
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);
}
