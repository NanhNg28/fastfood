package com.nanhng.FastFood.dto.request.banner;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateBannerReq {
    @NotNull
    Integer id;
    Integer imageId;
    String link;
}
