package com.nakanara.user.entity;

import com.nakanara.core.config.Role;
import com.nakanara.core.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "TB_USER")
@Data
@Builder
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userUid;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Column(nullable = false)
    private String name;

    private String nickname;

    private String email;

    private LocalDateTime lastAccessDt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public String getRoleValue() {
        return this.role.getValue();
    }
}
