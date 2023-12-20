package com.kbt1.ollilove.transferservice.dto;

import com.kbt1.ollilove.transferservice.domain.Transfer;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@Data
@AllArgsConstructor
public class HistoryDTO {
    private Long historyId;
    private Long transferId;
    private Long senderId;
    private Long receiverId;
    private String videoUrl;
    private Boolean isReply;
}
