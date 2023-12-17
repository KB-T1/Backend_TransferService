package com.kbt1.ollilove.transferservice.service;

import com.kbt1.ollilove.transferservice.domain.Transfer;
import com.kbt1.ollilove.transferservice.dto.TransferDTO;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface TransferService {
    Transfer getTransferById(TransferDTO transferDTO);
    List<Transfer> getTransferAllByUserId(TransferDTO transferDTO);
    TransferDTO createTransfer (TransferDTO transferDTO);
}
