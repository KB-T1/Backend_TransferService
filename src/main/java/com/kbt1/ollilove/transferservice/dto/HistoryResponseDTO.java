package com.kbt1.ollilove.transferservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Data
@AllArgsConstructor
public class HistoryResponseDTO {
    private Long historyId;
    private Long senderId;
    private Long receiverId;
    private String videoUrl;
    private Boolean isReply;
    private LocalDateTime createdAt;
    private Long amount;
}
