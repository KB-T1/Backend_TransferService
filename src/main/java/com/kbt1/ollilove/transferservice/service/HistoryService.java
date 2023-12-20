package com.kbt1.ollilove.transferservice.service;

import com.kbt1.ollilove.transferservice.domain.History;
import com.kbt1.ollilove.transferservice.domain.Transfer;
import com.kbt1.ollilove.transferservice.dto.HistoryDTO;
import com.kbt1.ollilove.transferservice.dto.HistoryResponseDTO;
import com.kbt1.ollilove.transferservice.dto.ResultDTO;

import java.util.List;

public interface HistoryService {
    //송금내역 보기
    ResultDTO<List<HistoryResponseDTO>> getHistoryListByUserIdAndCount(Long userId);
    ResultDTO<List<HistoryResponseDTO>> getHistoryListByUserIdAndCount(Long userId, Long count);

    //두명 간에 송금내역 보기
    ResultDTO<List<HistoryResponseDTO>> getHistoryListByUserIdWithTargetId(Long userId, Long targetUserId);
    ResultDTO<List<HistoryResponseDTO>> getHistoryListByUserIdWithTargetId(Long userId, Long targetUserId, Long count);
    History saveHistoryRecord(HistoryDTO historyDTO, Transfer transfer);
}
