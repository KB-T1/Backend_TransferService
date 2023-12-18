package com.kbt1.ollilove.transferservice.service;

import com.kbt1.ollilove.transferservice.dto.HistoryResponseDTO;

import java.util.List;

public interface HistoryService {
    List<HistoryResponseDTO> getHistoryByUserIdAndCount(Long userId, int count);
}
