package com.kbt1.ollilove.transferservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponseDTO {
    private Long accountId;
    private Long userId;
    private String bankName;
    private String accountNumber;
    private Long balance;
}
