package com.nakanara.user.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "TB_SNS_USER")
@Data
public class SNSUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long snsUserUid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "src_user_uid", unique = true, nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private String snsType;

    @Column(nullable = false, unique = true)
    private String snsId;

    private String snsName;

    private String nickName;

    private String snsProfile;

    private LocalDateTime snsConnectDate;


/*

    id=aynvlIl5dyulEW3JpeKJ5qML94IfH_N0Nxmz5x85bE0
    naver:91 nickname=undefined
    naver:92 name=박정우
    naver:93 email=nakanara@naver.com
    naver:94 gender=undefined
    naver:95 age=undefined
    naver:96 birthday=06-15
    naver:97 profile_image=undefined
    naver:98 birthyear=undefined
    naver:99 mobile=undefined*/

}
