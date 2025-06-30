package com.nanhng.FastFood.entity.blog;

import com.nanhng.FastFood.entity.BaseEntity;
import com.nanhng.FastFood.entity.upload_file.UploadFile;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "blog_contents")
public class BlogContent extends BaseEntity {
    @NotNull
    Integer blogId;
    String content;
    Integer uploadFileId;

    @Transient
    UploadFile uploadFile;

}
