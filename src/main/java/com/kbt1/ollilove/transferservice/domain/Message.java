package com.kbt1.ollilove.transferservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="message")
@Getter
@Setter
@NoArgsConstructor
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long messageId;

    @Column(name="sender_id")
    private Long senderId;

    @Column(name="receiver_id")
    private Long receiverId;

    @Column(name="video_url")
    private String videoUrl;

    @Column(name="transfer_id")
    private String transferId;
}
