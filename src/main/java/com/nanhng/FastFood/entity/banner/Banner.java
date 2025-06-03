package com.nanhng.FastFood.entity.banner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nanhng.FastFood.entity.BaseEntity;
import com.nanhng.FastFood.entity.upload_file.UploadFile;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Banner extends BaseEntity {
    Integer imageId;
    String link;

    @Transient
    UploadFile image;
}
