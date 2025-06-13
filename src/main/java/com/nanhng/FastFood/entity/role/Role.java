package com.nanhng.FastFood.entity.role;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.constant.RoleType;
import com.nanhng.FastFood.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role extends BaseEntity {
    int objectId;
    String name;
    String note;

    @Column(name = "type",columnDefinition = "INT")
    RoleType roleType;

}
