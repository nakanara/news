package com.nakanara.user.repository;

import com.nakanara.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(String userId);
}
