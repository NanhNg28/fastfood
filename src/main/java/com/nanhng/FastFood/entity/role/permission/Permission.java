package com.nanhng.FastFood.entity.role.permission;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.constant.RoleType;
import com.nanhng.FastFood.entity.BaseEntity;
import com.nanhng.FastFood.entity.role.constant.PermissionGroup;
import com.nanhng.FastFood.entity.role.constant.PermissionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permission extends BaseEntity {
    String title;
    @Enumerated(EnumType.STRING)
    PermissionType permission;

    @Column(name = "parent_permission")
    @Enumerated(EnumType.STRING)
    PermissionGroup parentPermission;

    @Column(name = "can_view")
    @JsonProperty("view")
    Boolean isView;

    @Column(name = "can_write")
    @JsonProperty("write")
    Boolean isWrite;

    @Column(name = "can_approval")
    @JsonProperty("approval")
    Boolean isApproval;

    @Column(name = "can_decision")
    @JsonProperty("decision")
    Boolean isDecision;

    @Column(name = "type", columnDefinition = "INT")
    RoleType type;

    @Column(name="status", columnDefinition = "INT")
    ActiveStatus status;

}
