package com.kbt1.ollilove.transferservice.dto;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Data
public class TransferDTO {
    private Long transferId;
    private Long amount;
    private Long senderId;
    private Long receiverID;
}
