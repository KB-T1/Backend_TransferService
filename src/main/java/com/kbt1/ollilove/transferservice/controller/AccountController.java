package com.kbt1.ollilove.transferservice.controller;

import com.kbt1.ollilove.transferservice.dto.AccountRequestDTO;
import com.kbt1.ollilove.transferservice.dto.AccountResponseDTO;
import com.kbt1.ollilove.transferservice.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transfer-api/account")
@RequiredArgsConstructor
@Tag(name="account", description = "계좌 API")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/new")
    @Operation(summary="신규 계좌번호 생성")
    public ResponseEntity createNewAccount(@RequestBody AccountRequestDTO accountRequestDTO) {
        AccountResponseDTO accountResponseDTO;
        try {
            accountResponseDTO = accountService.createNewAccount(accountRequestDTO.getUserId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("sql error");
        }
        return ResponseEntity.ok(accountResponseDTO);
    }

    //내 계좌정보 가져오기
    @GetMapping("/my")
    @Operation(summary="계좌 정보 조회")
    public ResponseEntity<AccountResponseDTO> getMyAccountInfo(@RequestParam Long userId) {
        AccountResponseDTO accountResponseDTO;
        accountResponseDTO = accountService.getAccountInfo(userId);
        return ResponseEntity.ok(accountResponseDTO);
    }

}
