package com.kbt1.ollilove.transferservice.service;

import com.kbt1.ollilove.transferservice.dto.VideoRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface VideoService {
    String saveVideo(VideoRequestDTO videoRequestDTO);
}
