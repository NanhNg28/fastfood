package com.nanhng.FastFood.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    Date updatedAt;

    private boolean deleted = false;

}
