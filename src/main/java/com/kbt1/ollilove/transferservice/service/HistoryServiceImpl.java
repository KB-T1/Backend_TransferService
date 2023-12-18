package com.kbt1.ollilove.transferservice.service;

import com.kbt1.ollilove.transferservice.domain.History;
import com.kbt1.ollilove.transferservice.dto.HistoryResponseDTO;
import com.kbt1.ollilove.transferservice.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;

    //송금내역 보기
    @Override
    public List<HistoryResponseDTO> getHistoryByUserIdAndCount(Long userId, int count) {

        List<History> histories = historyRepository.findBySenderIdOrReceiverIdOrderByRegDateDesc(userId, userId);
        List<HistoryResponseDTO> historyResDTOList = new ArrayList<>();

        int i = 0;
        for (History history : histories) {
            if (i == count) break;
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
            i++;
        }

        return historyResDTOList;
    }
}
