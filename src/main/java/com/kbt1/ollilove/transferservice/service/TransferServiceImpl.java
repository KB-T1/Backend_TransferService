package com.kbt1.ollilove.transferservice.service;

import com.kbt1.ollilove.transferservice.domain.Transfer;
import com.kbt1.ollilove.transferservice.dto.TransferDTO;
import com.kbt1.ollilove.transferservice.dto.TransferHistoryDTO;
import com.kbt1.ollilove.transferservice.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService{
    private final TransferRepository transferRepository;

    @Override
    public Transfer getTransferById(TransferDTO transferDTO) {
        return transferRepository.findById(transferDTO.getTransferId()).orElseThrow(()-> new RuntimeException("해당 User"));
    }

    @Override
    public List<Transfer> getTransferAllByUserId(TransferDTO transferDTO) {
        return transferRepository.findAllBySenderIdOrReceiverIdOrderByRegDateAsc(
                transferDTO.getSenderId(), transferDTO.getReceiverID()
        );
    }

    @Override
    public TransferDTO createTransfer(TransferDTO transferDTO) {
        return null;
    }
}
