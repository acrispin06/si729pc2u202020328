package com.leedarson.platform.u202020328.shared.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditableModel {
    @Getter
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate createdAt = LocalDate.now();

    @Getter
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDate updatedAt = LocalDate.now();
}
