package com.kbt1.ollilove.transferservice.controller;


import com.kbt1.ollilove.transferservice.dto.ResultDTO;
import com.kbt1.ollilove.transferservice.dto.TransferRequestDTO;
import com.kbt1.ollilove.transferservice.service.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/transfer-api/transfer")
@RequiredArgsConstructor
@Tag(name = "Transfer", description = "송금 API")
//@CrossOrigin({"http://localhost:3000", "http://ollilove.165.192.105.60.nip.io"})
public class TransferController {
    private final TransferService transferService;
//    private final VideoServiceImpl videoService;

    @Operation(summary = "영상+송금 보내기")
    @PostMapping(value = "/new", consumes = "multipart/form-data")
    public ResponseEntity<ResultDTO> createTransfer(TransferRequestDTO transferRequestDTO) {

        //송금보내기
        if (transferRequestDTO.getTransferId() == -1 && !transferRequestDTO.getVideo().isEmpty()) {
            return ResponseEntity.ok(transferService.createTransfer(transferRequestDTO));

        } //답장보내기
        else if (transferRequestDTO.getAmount() == -1 && !transferRequestDTO.getVideo().isEmpty()) {
            return ResponseEntity.ok(transferService.replyWithVideo(transferRequestDTO));

        } else return ResponseEntity.badRequest().body(ResultDTO.builder().success(false).build());
    }

//    @GetMapping("")
//    @Operation(summary="송금 기록")
//    public ResponseEntity<Transfer> getTransferById(@RequestParam("transferId") Long transferId) {
//        return ResponseEntity.ok(transferService.getTransferById(transferId));
//    }
//
//    @GetMapping("/transfer")
//    @Operation(summary="사용자의 송금 내역 보기")
//    public ResponseEntity<ResultDTO> getTransferAllByUserId (@RequestParam("userId") Long userId) {
//        return ResponseEntity.ok(transferService.getTransferAllByUserId(userId));
//    }
//    @PostMapping(value = "/video", consumes = "multipart/form-data")
//    @Operation(summary="비디오 업로드")
//    public ResponseEntity<String> uploadVideo(VideoRequestDTO videoRequestDTO) {
//        String videoUrl = videoService.saveVideo(videoRequestDTO);
//        return ResponseEntity.ok("Video uploaded successfully. Video URL: " + videoUrl);
//    }
}
