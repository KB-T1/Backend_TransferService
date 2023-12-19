package com.kbt1.ollilove.transferservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {
    private Long accountId;
    private Long userId;
    private String bankName;
    private String accountNumber;
    private Long balance;
}