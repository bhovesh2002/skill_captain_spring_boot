package com.UserDB.UserDB.Repository;

import com.UserDB.UserDB.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

}
