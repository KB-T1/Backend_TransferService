package com.kbt1.ollilove.transferservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class HistoryResponseDTO {
    private Long historyId;
    private Long senderId;
    private String senderName;
    private String senderNickName;
    private String senderProfile;
    private Long receiverId;
    private String receiverName;
    private String receiverNickName;
    private String receiverProfile;
    private String videoUrl;
    private Boolean isReply;
    private LocalDateTime createdAt;
    private Long amount;
}
