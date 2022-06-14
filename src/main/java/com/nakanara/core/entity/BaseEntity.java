package com.nakanara.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nakanara.user.entity.User;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "reg_emp_id")
    private User regEmpId;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date regDttm;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "mod_emp_id")
    private User modEmpId;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date modDttm;
}
