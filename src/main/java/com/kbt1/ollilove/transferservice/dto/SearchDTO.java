package com.kbt1.ollilove.transferservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchDTO {
    private Long userId;
    private Long senderId;
    private Long receiverId;
}
