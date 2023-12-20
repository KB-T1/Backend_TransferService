package com.kbt1.ollilove.transferservice.controller;

import com.kbt1.ollilove.transferservice.dto.HistoryResponseDTO;
import com.kbt1.ollilove.transferservice.dto.ResultDTO;
import com.kbt1.ollilove.transferservice.service.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transfer-api/history")
@RequiredArgsConstructor
@Tag(name="History", description = "내역 관련 API")
@CrossOrigin({"http://localhost:3000","http://ollilove.165.192.105.60.nip.io"})
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("")
    @Operation(summary = "마음 주고받은 내역 보기")
    public ResponseEntity<ResultDTO<List<HistoryResponseDTO>>> getHistoryListByUserId(@RequestParam("userId") Long userId, @RequestParam(value = "count", required = false) Long count) {
        ResultDTO<List<HistoryResponseDTO>> resData;
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
    @Operation(summary = "다른 사람과 주고받은 마음 보기")
    public ResponseEntity<ResultDTO<List<HistoryResponseDTO>>> getHistoryListByUserIdWithTargetId (@RequestParam("userId") Long userId, @RequestParam("targetUserId") Long targetUserID  ,@RequestParam(value = "count", required = false) Long count) {
        ResultDTO<List<HistoryResponseDTO>> resData;
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
