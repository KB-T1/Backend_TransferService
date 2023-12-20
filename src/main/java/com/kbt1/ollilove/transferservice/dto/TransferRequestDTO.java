package com.kbt1.ollilove.transferservice.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferRequestDTO {
    private Long amount;
    private Long senderId;
    private Long receiverId;
    private MultipartFile video;
}
