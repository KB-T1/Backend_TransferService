package com.kbt1.ollilove.transferservice.service;

import com.kbt1.ollilove.transferservice.domain.Account;
import com.kbt1.ollilove.transferservice.dto.AccountResponseDTO;
import com.kbt1.ollilove.transferservice.dto.ResultDTO;
import com.kbt1.ollilove.transferservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;

    @Override
    public ResultDTO<AccountResponseDTO> createNewAccount(Long userId) {
        Account account = generateRandomAccountBase();
        account.setUserId(userId);
        accountRepository.save(account);
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO(
                account.getAccountId(),
                account.getUserId(),
                account.getBankName(),
                account.getAccountNumber(),
                account.getBalance()
        );
        return ResultDTO.<AccountResponseDTO>builder()
                .success(true)
                .data(accountResponseDTO)
                .build();
    }

    @Override
    public ResultDTO<AccountResponseDTO> getAccountInfo(Long userId) {
        Account account = accountRepository.findByUserId(userId);
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO(
                account.getAccountId(),
                account.getUserId(),
                account.getBankName(),
                account.getAccountNumber(),
                account.getBalance()
        );
        return ResultDTO.<AccountResponseDTO>builder()
                .success(true)
                .data(accountResponseDTO)
                .build();
    }


    //계좌번호 설정
    private static Account generateRandomAccountBase() {
        Account accountBase = new Account();
        Random random = new Random();

        int firstPart = random.nextInt(900000) + 100000;
        int secondPart = random.nextInt(90) + 10;
        int thirdPart = random.nextInt(90000) + 10000;
        accountBase.setAccountNumber(String.format("%06d-%02d-%06d", firstPart, secondPart, thirdPart));

        Random randomNum = new Random();
        accountBase.setBalance(ThreadLocalRandom.current().nextLong(500000, 1500000 + 1));

        accountBase.setBankName("국민은행");
        return accountBase;
    }

}
