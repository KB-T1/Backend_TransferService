package com.kbt1.ollilove.transferservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="transfer")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transfer extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long transferId;

    @Column(name="amount")
    private Long amount;

    @Column(name="sender_id")
    private Long senderId;

    @Column(name="receiver_id")
    private Long receiverId;
}
