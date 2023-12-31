package com.kbt1.ollilove.transferservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
// Entity 변화 감시
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public class BaseTimeEntity {
    @CreatedDate
    @Column(name="created_at", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name="modified_at")
    private LocalDateTime modDate;
}