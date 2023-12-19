package com.kbt1.ollilove.transferservice.service;

import com.kbt1.ollilove.transferservice.dto.HistoryResponseDTO;

import java.util.List;

public interface HistoryService {
    //송금내역 보기
    List<HistoryResponseDTO> getHistoryListByUserIdAndCount(Long userId);
    List<HistoryResponseDTO> getHistoryListByUserIdAndCount(Long userId, Long count);

    //두명 간에 송금내역 보기
    List<HistoryResponseDTO> getHistoryListByUserIdWithTargetId(Long userId, Long targetUserId);
    List<HistoryResponseDTO> getHistoryListByUserIdWithTargetId(Long userId, Long targetUserId, Long count);
}
