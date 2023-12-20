package com.kbt1.ollilove.transferservice.controller;

import com.kbt1.ollilove.transferservice.dto.AccountResponseDTO;
import com.kbt1.ollilove.transferservice.dto.ResultDTO;
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
@CrossOrigin("http://localhost:3000")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/new")
    @Operation(summary="신규 계좌번호 생성")
    public ResponseEntity<ResultDTO> createNewAccount(@RequestParam Long userId) {
        return ResponseEntity.ok(accountService.createNewAccount(userId));
    }

    //내 계좌정보 가져오기
    @GetMapping("/my")
    @Operation(summary="계좌 정보 조회")
    public ResponseEntity<ResultDTO> getMyAccountInfo(@RequestParam Long userId) {
        return ResponseEntity.ok(accountService.getAccountInfo(userId));
    }

}
