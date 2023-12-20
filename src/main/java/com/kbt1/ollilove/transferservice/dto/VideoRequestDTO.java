package com.kbt1.ollilove.transferservice.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoRequestDTO {
    private Long senderId;
    private Long receiverId;
    private MultipartFile video;
}
