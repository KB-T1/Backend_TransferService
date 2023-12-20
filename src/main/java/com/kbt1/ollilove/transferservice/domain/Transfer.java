package com.kbt1.ollilove.transferservice.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name="transfer")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transfer extends BaseTimeEntity {
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
