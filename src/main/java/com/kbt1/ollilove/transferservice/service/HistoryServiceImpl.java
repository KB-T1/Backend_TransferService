package com.kbt1.ollilove.transferservice.service;

import com.kbt1.ollilove.transferservice.domain.History;
import com.kbt1.ollilove.transferservice.domain.Transfer;
import com.kbt1.ollilove.transferservice.dto.HistoryResponseDTO;
import com.kbt1.ollilove.transferservice.dto.ResultDTO;
import com.kbt1.ollilove.transferservice.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;
    @Value("${api-url.user}")
    private String USER_API_BASE_URL;

    //마음 내역 보기
    @Override
    @Transactional
    public ResultDTO<List<HistoryResponseDTO>> getHistoryListByUserIdAndCount(Long userId) {
        List<HistoryResponseDTO> historyFinalList = getHistoryList(userId);
        return ResultDTO.<List<HistoryResponseDTO>>builder()
                .success(true)
                .data(historyFinalList)
                .build();
    }

    //개수 정해서 마음 내역 보기
    @Override
    @Transactional
    public ResultDTO<List<HistoryResponseDTO>> getHistoryListByUserIdAndCount(Long userId, Long count) {
        List<HistoryResponseDTO> historyFinalList = getHistoryList(userId);
        return getListResultDTO(count, historyFinalList);
    }

    //userID 와 targetUserID간 마음 내역 보기
    @Override
    @Transactional
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
    @Transactional
    public ResultDTO<List<HistoryResponseDTO>> getHistoryListByUserIdWithTargetId(Long userId, Long targetUserId, Long count) {
        List<HistoryResponseDTO> historyResDTOList = getHistoryListByUserIdAndCount(userId).getData();
        List<HistoryResponseDTO> filteredHistoryDTOs = historyResDTOList
                .stream()
                .filter(historyDTO -> historyDTO.getSenderId().equals(targetUserId) || historyDTO.getReceiverId().equals(targetUserId))
                .collect(Collectors.toList());
        return getListResultDTO(count, filteredHistoryDTOs);
    }

    //개수에 알맞게 ResultDTO 수정
    private ResultDTO<List<HistoryResponseDTO>> getListResultDTO(Long count, List<HistoryResponseDTO> filteredHistoryDTOs) {
        if (count > filteredHistoryDTOs.size()) {
            return ResultDTO.<List<HistoryResponseDTO>>builder()
                    .success(true)
                    .data(filteredHistoryDTOs)
                    .build();
        }
        return ResultDTO.<List<HistoryResponseDTO>>builder()
                .success(true)
                .data(filteredHistoryDTOs.subList(0, count.intValue()))
                .build();
    }

    //히스토리 기록하기
    @Transactional
    public History saveHistoryRecord(Transfer transfer, Boolean isReply, String videoUrl) {
        //Transfer 조회
        History history = History.builder()
                .senderId(transfer.getSenderId())
                .receiverId(transfer.getReceiverId())
                .transferId(transfer)
                .videoUrl(videoUrl)
                .isReply(isReply)
                .build();
        return historyRepository.save(history);
    }


    //내역 긁어오기
    private List<History> getHistoryListByUserID(Long userId) {
        return historyRepository.findBySenderIdOrReceiverIdOrderByRegDateDesc(userId, userId);
    }

    //내역에 송금 금액 불러와 작성하기
    private List<HistoryResponseDTO> addAmountAtTransferHistory(List<History> histories) {
        List<HistoryResponseDTO> historyResDTOList = new ArrayList<>();

        for (History history : histories) {
            HistoryResponseDTO historyResDTO = HistoryResponseDTO.builder()
                    .historyId(history.getHistoryId())
                    .senderId(history.getSenderId())
                    .receiverId(history.getReceiverId())
                    .videoUrl(history.getVideoUrl())
                    .isReply(history.getIsReply())
                    .createdAt(history.getRegDate())
                    .amount(0L)
                    .build();
            if (!history.getIsReply()) {
                Long amount = history.getTransferId().getAmount();
                historyResDTO.setAmount(amount);
            }
            historyResDTOList.add(historyResDTO);
        }
        return historyResDTOList;
    }

    private List<HistoryResponseDTO> addFamilyInfoAtTransferHistory(List<HistoryResponseDTO> historyResList, Long userId) {
        LinkedHashMap familyMemberResponse = fetchFamilyInfoByUserID(userId);
        if (familyMemberResponse.get("familyMember") != null) {
            List<LinkedHashMap> familyMemberList = (List) familyMemberResponse.get("familyMember");
            Map<Long, Object> familyMap = new HashMap<>();
            for (int i = 0; i < familyMemberList.size(); i++) {
                familyMap.put(Long.valueOf((int) familyMemberList.get(i).get("userId")), familyMemberList.get(i));
            }
            for (HistoryResponseDTO historyRes : historyResList) {
                LinkedHashMap senderInfo = (LinkedHashMap) familyMap.get(historyRes.getSenderId());
                LinkedHashMap receiverInfo = (LinkedHashMap) familyMap.get(historyRes.getReceiverId());
                historyRes.setSenderName((String) senderInfo.get("userName"));
                historyRes.setSenderNickName((String) senderInfo.get("nickName"));
                historyRes.setSenderProfile((String) senderInfo.get("profile"));
                historyRes.setReceiverName((String) receiverInfo.get("userName"));
                historyRes.setReceiverNickName((String) receiverInfo.get("receiverName"));
                historyRes.setReceiverProfile((String) receiverInfo.get("profile"));
            }
        }
        return historyResList;
    };

    //유저 서비스 -> 가족 정보 가져오기
    private LinkedHashMap fetchFamilyInfoByUserID (Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(USER_API_BASE_URL+ "/family/user/" + userId);
        ResultDTO result = restTemplate.getForObject(builder.toUriString(), ResultDTO.class);
        return (LinkedHashMap) result.getData();
    }

    //히스토리에 가족 송금 정보 붙혀서 가져오기
    private List<HistoryResponseDTO> getHistoryList (Long userId) {
        List<HistoryResponseDTO> historyResDTOList = addAmountAtTransferHistory(getHistoryListByUserID(userId));
        List<HistoryResponseDTO> historyFinalList = addFamilyInfoAtTransferHistory(historyResDTOList, userId);
        return historyFinalList;
    }

}
