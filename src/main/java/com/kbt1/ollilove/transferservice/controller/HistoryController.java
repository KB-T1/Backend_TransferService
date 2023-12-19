package com.kbt1.ollilove.transferservice.controller;

import com.kbt1.ollilove.transferservice.dto.HistoryResponseDTO;
import com.kbt1.ollilove.transferservice.service.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("transfer-api/history")
@RequiredArgsConstructor
@Tag(name="History", description = "내역 관련 API")
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("")
    @Operation(summary = "마음 주고받은 내역 보기")
    public ResponseEntity<List<HistoryResponseDTO>> getHistoryAllByUserId (@RequestParam("userId") Long userId, @RequestParam("count") int count) {
        List<HistoryResponseDTO> resData = historyService.getHistoryByUserIdAndCount(userId, count);
        return ResponseEntity.ok(resData);
    }
}
