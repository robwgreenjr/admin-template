package com.greenbeard.scheduling.users.repository;

import com.greenbeard.scheduling.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from app_user where login = ?1", nativeQuery = true)
    User findByLogin(String theLogin);
}
