package com.kbt1.ollilove.transferservice.service;

import com.kbt1.ollilove.transferservice.domain.History;
import com.kbt1.ollilove.transferservice.domain.Transfer;
import com.kbt1.ollilove.transferservice.dto.*;
import com.kbt1.ollilove.transferservice.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService{
    private final TransferRepository transferRepository;
    private final HistoryService historyService;
    private final VideoService videoService;

    @Override
    public Transfer getTransferById(Long transferId) {
        return transferRepository.findByTransferId(transferId);
    }

    @Override
    public ResultDTO<List<Transfer>> getTransferAllByUserId(Long userId) {
        List<Transfer> transferList = transferRepository.findAllBySenderIdOrReceiverIdOrderByRegDateAsc(
                userId, userId
        );
        return ResultDTO.<List<Transfer>>builder()
                .success(true)
                .data(transferList)
                .build();
    }

    //송금 보내기
    @Override
    @Transactional
    public ResultDTO<History> createTransfer(TransferRequestDTO transferRequestDTO) {
        //Transfer DB에 저장
        Long transferId = saveTransferRecordAndGetTransferId(transferRequestDTO);

        //비디오 저장
        VideoRequestDTO videoRequestDTO = VideoRequestDTO.builder()
                .senderId(transferRequestDTO.getSenderId())
                .receiverId(transferRequestDTO.getReceiverId())
                .video(transferRequestDTO.getVideo())
                .build();
        String videoURL = videoService.saveVideo(videoRequestDTO);

        //히스토리 내역 삽입
        HistoryDTO historyDTO = HistoryDTO.builder()
                .transferId(transferId)
                .senderId(transferRequestDTO.getSenderId())
                .receiverId(transferRequestDTO.getReceiverId())
                .videoUrl(videoURL)
                .isReply(false)
                .build();

        History history = historyService.saveHistoryRecord(historyDTO, getTransferById(transferId));
        return ResultDTO.<History>builder()
                .success(true)
                .data(history)
                .build();
    }

    //송금 DB에 저장하기
    private Long saveTransferRecordAndGetTransferId (TransferRequestDTO transferRequestDTO) {
        Transfer newTransfer = transferRepository.save(Transfer.builder()
                .amount(transferRequestDTO.getAmount())
                .senderId(transferRequestDTO.getSenderId())
                .receiverId(transferRequestDTO.getReceiverId())
                .build());
        return newTransfer.getTransferId();
    }
}
