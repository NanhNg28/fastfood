package com.nanhng.FastFood.entity.upload_file;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nanhng.FastFood.entity.BaseEntity;
import com.nanhng.FastFood.entity.upload_file.constant.UploadFileType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "upload_files")
public class UploadFile extends BaseEntity {

    @Column(name = "origin_file_path")
    String originFilePath;
    @Column(name = "thumb_file_path")
    String thumbFilePath;

    @Column(name = "origin_file_name")
    String originalFileName;
    @Column(name = "thumb_file_name")
    String thumbFileName;

    @Column(columnDefinition = "INT")
    UploadFileType type;

    Integer width;
    Integer height;
    Integer duration;
    Long size;

}
