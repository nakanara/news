package com.nakanara.user.repository;

import com.nakanara.user.entity.SNSUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SNSUserRepository extends JpaRepository<SNSUserEntity, Long> {

}
