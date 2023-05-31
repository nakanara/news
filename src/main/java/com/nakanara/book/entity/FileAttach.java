package com.nakanara.book.entity;

import com.nakanara.core.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Setter @Getter
@MappedSuperclass
public class FileAttach extends BaseEntity {

    @Id
    @Column(name = "att_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long fileAttachUid;

    private String filename;

    private String fileExt;

    private long fileSize;

    private String fileType;

}
