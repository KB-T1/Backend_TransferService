package com.kbt1.ollilove.transferservice.service;

import com.kbt1.ollilove.transferservice.dto.VideoRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;


@Service
public class VideoServiceImpl implements VideoService{

    private String uploadDir = "/"; // application.properties에 설정된 업로드 디렉토리

    public String saveVideo(VideoRequestDTO videoRequestDTO) {
        // 고유한 주소값 생성
        String uniqueAddress = generateUniqueAddress(videoRequestDTO.getSenderId(), videoRequestDTO.getReceiverId(), videoRequestDTO.getRegDate());

        // 저장될 파일 경로
        String filePath = uploadDir + File.separator + uniqueAddress + ".webm";

        // 동영상 파일 저장
        try {
            saveVideoFile(videoRequestDTO.getVideo(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
            // 예외 처리 로직 추가
            return "Error uploading video.";
        }

        return filePath; // 또는 DB에 저장할 경우 저장된 주소를 반환
    }

    private String generateUniqueAddress(Long sender, Long receiver, LocalDateTime regDate) {
        return sender + "/" + receiver + "/" + regDate;
    }

    private void saveVideoFile(MultipartFile videoFile, String filePath) throws IOException {
        // 동영상 파일 저장 로직
        Path path = Paths.get(filePath);
        videoFile.transferTo(path);
    }
}
