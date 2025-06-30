package com.nanhng.FastFood.entity.blog;

import com.nanhng.FastFood.entity.BaseEntity;
import com.nanhng.FastFood.entity.upload_file.UploadFile;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "blogs")
public class Blog extends BaseEntity {
    String title;
    Integer authorId;
    Integer thumbnailImageId;

    @Transient
    UploadFile thumbnail;
    String introduction;

    @Transient
    List<BlogContent> listContents;
}
