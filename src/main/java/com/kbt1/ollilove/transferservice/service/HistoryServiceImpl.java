package com.kbt1.ollilove.transferservice.service;

import com.kbt1.ollilove.transferservice.domain.History;
import com.kbt1.ollilove.transferservice.dto.HistoryResponseDTO;
import com.kbt1.ollilove.transferservice.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;

    //마음 내역 보기
    @Override
    public List<HistoryResponseDTO> getHistoryListByUserIdAndCount(Long userId) {
        List<HistoryResponseDTO> historyResDTOList = addAmountAtTransferHistory(getHistoryListByUserID(userId));
        return historyResDTOList;
    }

    //개수 정해서 마음 내역 보기
    @Override
    public List<HistoryResponseDTO> getHistoryListByUserIdAndCount(Long userId, Long count) {
        List<HistoryResponseDTO> historyResDTOList = addAmountAtTransferHistory(getHistoryListByUserID(userId));
        return historyResDTOList.subList(0, count.intValue());
    }

    //userID 와 targetUserID간에 마음 내역 보기
    @Override
    public List<HistoryResponseDTO> getHistoryListByUserIdWithTargetId(Long userId, Long targetUserId) {
        List<HistoryResponseDTO> historyDTOs = getHistoryListByUserIdAndCount(userId);
        List<HistoryResponseDTO> filteredHistoryDTOs = historyDTOs
                .stream()
                .filter(historyDTO -> historyDTO.getSenderId() == targetUserId || historyDTO.getReceiverId() == targetUserId)
                .collect(Collectors.toList());
        return filteredHistoryDTOs;
    }

    //userID 와 targetUserID간에 마음 내역 보기 - 개수 정해서
    @Override
    public List<HistoryResponseDTO> getHistoryListByUserIdWithTargetId(Long userId, Long targetUserId, Long count) {
        List<HistoryResponseDTO> historyDTOs = getHistoryListByUserIdAndCount(userId);
        List<HistoryResponseDTO> filteredHistoryDTOs = historyDTOs
                .stream()
                .filter(historyDTO -> historyDTO.getSenderId() == targetUserId || historyDTO.getReceiverId() == targetUserId)
                .collect(Collectors.toList());
        return filteredHistoryDTOs.subList(0, count.intValue());
    }



    //내역 긁어오기
    private List<History> getHistoryListByUserID (Long userId) {
            return historyRepository.findBySenderIdOrReceiverIdOrderByRegDateDesc(userId, userId);
    }

    //송금 금액 더하기
    private List<HistoryResponseDTO> addAmountAtTransferHistory(List<History> histories) {
        List<HistoryResponseDTO> historyResDTOList = new ArrayList<>();

        for (History history : histories) {
            HistoryResponseDTO historyResDTO = new HistoryResponseDTO(
                    history.getHistoryId(),
                    history.getSenderId(),
                    history.getReceiverId(),
                    history.getVideoUrl(),
                    history.getIsReply(),
                    history.getRegDate(), 0L
            );
            if (!history.getIsReply()) {
                Long amount = history.getTransferId().getAmount();
                historyResDTO.setAmount(amount);
            }
            historyResDTOList.add(historyResDTO);
        }
        return historyResDTOList;
    }
}
