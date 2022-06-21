package com.nakanara.log.entity;

import com.nakanara.core.entity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "ELG_LOGIN")
public class LoginLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long loginUid;

    private String ip;

    private String userId;

    private String sessionId;

    public LoginLog(HttpServletRequest request, String userId){
        HttpSession session = request.getSession();

        this.ip = request.getRemoteAddr();
        this.sessionId = session.getId();
        this.userId = userId;

    }

}
