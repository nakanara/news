package com.nakanara.book.entity;

import com.nakanara.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "TB_FILE_ATTACH")
public class FileAttach extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long fileAttachUid;

    private String filename;

    private String fileExt;

    private long fileSize;

    private String fileType;

    /**
     * 부모 Key
     */
    @Column(name = "src_id")
    private String srcId;

}
