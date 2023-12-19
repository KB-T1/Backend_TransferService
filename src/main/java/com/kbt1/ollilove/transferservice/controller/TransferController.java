package com.kbt1.ollilove.transferservice.controller;


import com.kbt1.ollilove.transferservice.domain.Transfer;
import com.kbt1.ollilove.transferservice.dto.TransferDTO;
import com.kbt1.ollilove.transferservice.dto.VideoRequestDTO;
import com.kbt1.ollilove.transferservice.service.TransferService;
import com.kbt1.ollilove.transferservice.service.VideoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/transfer-api/transfer")
@RequiredArgsConstructor
@Tag(name="Transfer", description = "송금 API")
public class TransferController {
    private final TransferService transferService;
    private final VideoServiceImpl videoService;

    @GetMapping("")
    @Operation(summary="송금 기록")
    public ResponseEntity<Transfer> getTransferById(@RequestParam("transferId") Long transferId) {
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setTransferId(transferId);
        Transfer transfer = transferService.getTransferById(transferDTO);
        return ResponseEntity.ok(transfer);
    }

    @GetMapping("/transfer")
    @Operation(summary="사용자의 송금 내역 보기")
    public ResponseEntity<List<Transfer>> getTransferAllByUserId (@RequestParam("userId") Long userId) {
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setSenderId(userId);
        transferDTO.setReceiverID(userId);
        List<Transfer> resData = transferService.getTransferAllByUserId(transferDTO);
        return ResponseEntity.ok(resData);
    }

    @PostMapping("/new")
    public TransferDTO createTransfer (@RequestBody TransferDTO transferDTO) {

        return null;
    }

    @PostMapping(value = "/video", consumes = "multipart/form-data")
    @Operation(summary="비디오 업로드")
    public ResponseEntity<String> uploadVideo(VideoRequestDTO videoRequestDTO) {
        String videoUrl = videoService.saveVideo(videoRequestDTO);
        return ResponseEntity.ok("Video uploaded successfully. Video URL: " + videoUrl);
    }

//    @DeleteMapping("/video")
//    @Operation(summary="비디오 다시 찍기")
//    public ResponseEntity deleteVideo(@RequestParam String videoUrl) {
//        videoService.deleteVideo(videoUrl);
//
//    }
}
