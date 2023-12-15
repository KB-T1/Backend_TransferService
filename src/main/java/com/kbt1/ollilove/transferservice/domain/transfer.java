package com.kbt1.ollilove.transferservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="transfer")
@Entity
@Getter
@NoArgsConstructor
public class transfer extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long transferId;

    @Column(name="amount")
    private long amount;

    @Column(name="sender_id")
    private int senderId;

    @Column(name="receiver_id")
    private int receiverId;
}
