package com.kbt1.ollilove.transferservice.controller;

import com.kbt1.ollilove.transferservice.dto.AccountResponseDTO;
import com.kbt1.ollilove.transferservice.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transfer-api/account")
@RequiredArgsConstructor
@Tag(name="account", description = "계좌 API")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/new")
    public ResponseEntity<AccountResponseDTO> createNewAccount() {
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        return ResponseEntity.ok(accountResponseDTO);
    }

    @GetMapping("/my")
    public ResponseEntity<AccountResponseDTO> getMyAccountInfo() {
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        return ResponseEntity.ok(accountResponseDTO);
    }
}
