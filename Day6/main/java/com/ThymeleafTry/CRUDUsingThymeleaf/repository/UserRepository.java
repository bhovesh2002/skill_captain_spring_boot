package com.ThymeleafTry.CRUDUsingThymeleaf.repository;

import com.ThymeleafTry.CRUDUsingThymeleaf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
