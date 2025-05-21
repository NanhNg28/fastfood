package com.nanhng.FastFood.entity.role.role_permission;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nanhng.FastFood.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRole extends BaseEntity {

    @Column(name = "role_id")
    int roleId;

    @Column(name = "permission_id")
    int permissionId;

    @JsonProperty(value = "view")
    @Column(name = "can_view")
    Boolean isView;

    @JsonProperty(value = "write")
    @Column(name = "can_write")
    Boolean isWrite;

    @JsonProperty(value = "approval")
    @Column(name = "can_approval")
    Boolean isApproval;

    @JsonProperty(value = "decision")
    @Column(name = "can_decision")
    Boolean isDecision;
}
