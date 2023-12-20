package com.kbt1.ollilove.transferservice.service;

import com.kbt1.ollilove.transferservice.domain.History;
import com.kbt1.ollilove.transferservice.domain.Transfer;
import com.kbt1.ollilove.transferservice.dto.FamilyMemberDTO;
import com.kbt1.ollilove.transferservice.dto.HistoryDTO;
import com.kbt1.ollilove.transferservice.dto.HistoryResponseDTO;
import com.kbt1.ollilove.transferservice.dto.ResultDTO;
import com.kbt1.ollilove.transferservice.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;
    @Value("${api.user}")
    private String USER_API_BASE_URL;

    //마음 내역 보기
    @Override
    public ResultDTO<List<HistoryResponseDTO>> getHistoryListByUserIdAndCount(Long userId) {
        List<HistoryResponseDTO> historyResDTOList = addAmountAtTransferHistory(getHistoryListByUserID(userId));
        return ResultDTO.<List<HistoryResponseDTO>>builder()
                .success(true)
                .data(historyResDTOList)
                .build();
    }

    //개수 정해서 마음 내역 보기
    @Override
    public ResultDTO<List<HistoryResponseDTO>> getHistoryListByUserIdAndCount(Long userId, Long count) {
        List<HistoryResponseDTO> historyResDTOList = addAmountAtTransferHistory(getHistoryListByUserID(userId));
        return ResultDTO.<List<HistoryResponseDTO>>builder()
                .success(true)
                .data(historyResDTOList.subList(0, count.intValue()))
                .build();
    }

    //userID 와 targetUserID간 마음 내역 보기
    @Override
    public ResultDTO<List<HistoryResponseDTO>> getHistoryListByUserIdWithTargetId(Long userId, Long targetUserId) {
        List<HistoryResponseDTO> historyResDTOList = getHistoryListByUserIdAndCount(userId).getData();
        List<HistoryResponseDTO> filteredHistoryDTOs = historyResDTOList
                .stream()
                .filter(historyDTO -> historyDTO.getSenderId().equals(targetUserId) || historyDTO.getReceiverId().equals(targetUserId))
                .collect(Collectors.toList());
        return ResultDTO.<List<HistoryResponseDTO>>builder()
                .success(true)
                .data(filteredHistoryDTOs)
                .build();
    }

    //userID 와 targetUserID간에 마음 내역 보기 - 개수 정해서
    @Override
    public ResultDTO<List<HistoryResponseDTO>> getHistoryListByUserIdWithTargetId(Long userId, Long targetUserId, Long count) {
        List<HistoryResponseDTO> historyResDTOList = getHistoryListByUserIdAndCount(userId).getData();
        List<HistoryResponseDTO> filteredHistoryDTOs = historyResDTOList
                .stream()
                .filter(historyDTO -> historyDTO.getSenderId().equals(targetUserId) || historyDTO.getReceiverId().equals(targetUserId))
                .collect(Collectors.toList());
        return ResultDTO.<List<HistoryResponseDTO>>builder()
                .success(true)
                .data(filteredHistoryDTOs.subList(0, count.intValue()))
                .build();
    }
    //히스토리 기록하기
    @Transactional
    public History saveHistoryRecord (HistoryDTO historyDTO, Transfer transfer) {
        //Transfer 조회
        History history = History.builder()
                .senderId(historyDTO.getSenderId())
                .receiverId(historyDTO.getReceiverId())
                .transferId(transfer)
                .videoUrl(historyDTO.getVideoUrl())
                .isReply(historyDTO.getIsReply())
                .build();
        return historyRepository.save(history);
    }

    //내역 긁어오기
    private List<History> getHistoryListByUserID (Long userId) {
            return historyRepository.findBySenderIdOrReceiverIdOrderByRegDateDesc(userId, userId);
    }

    //내역에 송금 금액 불러와 작성하기
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

    //유저 서비스 -> 가족 정보 가져오기
    private HashMap<Long, FamilyMemberDTO> fetchFamilyInfoByUserID (Long userId) {
        RestTemplate restTemplate = new RestTemplate();


        FamilyMemberDTO result = restTemplate.getForObject(USER_API_BASE_URL, FamilyMemberDTO.class);
        HashMap<Long, FamilyMemberDTO> familyMap = new HashMap<Long, FamilyMemberDTO>();
        return familyMap;
    }


}
