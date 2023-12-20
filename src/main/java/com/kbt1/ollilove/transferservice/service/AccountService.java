package com.kbt1.ollilove.transferservice.service;

import com.kbt1.ollilove.transferservice.dto.AccountResponseDTO;
import com.kbt1.ollilove.transferservice.dto.ResultDTO;

public interface AccountService {
    ResultDTO<AccountResponseDTO> createNewAccount (Long userId);
    ResultDTO<AccountResponseDTO> getAccountInfo(Long userId);
}
