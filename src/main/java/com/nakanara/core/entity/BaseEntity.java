package com.nakanara.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nakanara.user.entity.UserEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity  {

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "reg_emp_id")
    private UserEntity regEmpId;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date regDttm;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "mod_emp_id")
    private UserEntity modEmpId;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date modDttm;


    /* 해당 엔티티를 저장하기 이전에 실행 */
    @PrePersist
    public void onPrePersist(){
//        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
//        this.modifiedDate = this.createdDate;
    }

    /* 해당 엔티티를 업데이트 하기 이전에 실행*/
    @PreUpdate
    public void onPreUpdate(){
//        this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
}
