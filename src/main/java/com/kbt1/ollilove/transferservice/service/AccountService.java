package com.kbt1.ollilove.transferservice.service;

import com.kbt1.ollilove.transferservice.dto.AccountResponseDTO;

public interface AccountService {
    AccountResponseDTO createNewAccount (Long userId);

}
