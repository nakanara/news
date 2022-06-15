package com.nakanara.user.entity;

import com.nakanara.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "USER")
@Data
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userUid;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String name;

    private String mail;

    private String password;

    private LocalDateTime lastAccessDt;

}
