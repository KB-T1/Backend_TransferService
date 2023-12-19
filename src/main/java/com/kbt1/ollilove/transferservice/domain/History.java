package com.kbt1.ollilove.transferservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="history")
@Getter
@Setter
@NoArgsConstructor
public class History extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long historyId;

    @Column(name="sender_id")
    private Long senderId;

    @Column(name="receiver_id")
    private Long receiverId;

    @Column(name="video_url")
    private String videoUrl;

    @Column(name="is_reply", columnDefinition = "tinyInt(1)")
    private Boolean isReply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transfer_id",nullable = false)
    private Transfer transferId;
}
