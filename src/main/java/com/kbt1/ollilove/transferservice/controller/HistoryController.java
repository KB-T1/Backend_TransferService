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
    public ResponseEntity<List<HistoryResponseDTO>> getHistoryListByUserId(@RequestParam("userId") Long userId, @RequestParam(value = "count", required = false) Long count) {
        List<HistoryResponseDTO> resData;
        if (count == null) {
            //값이 있는 경우
            resData = historyService.getHistoryListByUserIdAndCount(userId);
        } else {
            //값이 비어있는 경우
            resData = historyService.getHistoryListByUserIdAndCount(userId, count);
        }

        return ResponseEntity.ok(resData);
    }

    @GetMapping("/with")
    @Operation(summary = "마음 주고받은 내역 보기")
    public ResponseEntity<List<HistoryResponseDTO>> getHistoryListByUserIdWithTargetId (@RequestParam("userId") Long userId, @RequestParam("targetUserId") Long targetUserID  ,@RequestParam(value = "count", required = false) Long count) {
        List<HistoryResponseDTO> resData;
        if (count == null) {
             resData = historyService.getHistoryListByUserIdWithTargetId(userId, targetUserID);
        } else {
            resData = historyService.getHistoryListByUserIdWithTargetId(userId, targetUserID, count);
        }

        return ResponseEntity.ok(resData);
    }

//    @GetMapping("/shorts")
//    @Operation(summary = "주고 받은 영상 내역 보기")
//    public ResponseEntity<List<>>

}
