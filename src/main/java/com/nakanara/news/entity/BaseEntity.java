package com.nakanara.news.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Setter
@Getter
@MappedSuperclass // Super 클래스로 별도 Table 생성되지 않는 옵션
@EntityListeners(AuditingEntityListener.class)  // Audit 기능을 줄 경우 자동으로 @Create @LastModify 값 설정
public abstract class BaseEntity {

    @CreatedBy
    private String regEmpId;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date regDttm;

    @LastModifiedBy
    private String modEmpId;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date modDttm;
}
