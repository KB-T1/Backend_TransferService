package com.kbt1.ollilove.transferservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class VideoRequestDTO {
    private Long senderId;
    private Long receiverId;
    private MultipartFile video;
}
