package com.kbt1.ollilove.transferservice.service;

import com.kbt1.ollilove.transferservice.domain.History;
import com.kbt1.ollilove.transferservice.domain.Transfer;
import com.kbt1.ollilove.transferservice.dto.ResultDTO;
import com.kbt1.ollilove.transferservice.dto.TransferRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransferService {
    Transfer getTransferById(Long transferId);

    ResultDTO<History> createTransfer(TransferRequestDTO transferRequestDTO);

    ResultDTO<History> replyWithVideo(TransferRequestDTO transferRequestDTO);
}
