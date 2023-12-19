package com.kbt1.ollilove.transferservice.service;

import com.kbt1.ollilove.transferservice.dto.VideoRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Service
public class VideoServiceImpl implements VideoService{

    @Value("${upload.dir}")
    private String uploadDir; //  수정 요함

    public String saveVideo(VideoRequestDTO videoRequestDTO) {
        // 고유한 주소값 생성
        String uniqueAddress = generateUniqueAddress(videoRequestDTO.getSenderId(), videoRequestDTO.getReceiverId());
        // 저장될 파일 경로
        String filePath = uploadDir + File.separator + uniqueAddress;

        File directory = new File(filePath);
        if (!directory.exists()) {
            boolean success = directory.mkdirs();
            if (success) {
                System.out.println("Directory created successfully: " + filePath);
            } else {
                System.err.println("Failed to create directory: " + filePath);
            }
        } else {
            System.out.println("Directory already exists: " + filePath);
        }


        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "-" + videoRequestDTO.getVideo().getOriginalFilename();
        // 동영상 파일 저장
        try {
            File saveFile = new File(filePath, fileName);
            if (!saveFile.exists()) {
                saveVideoFile(videoRequestDTO.getVideo(), saveFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // 예외 처리 로직 추가
            return "Error uploading video.";
        }

        return filePath; // 또는 DB에 저장할 경우 저장된 주소를 반환
    }

    private String generateUniqueAddress(Long sender, Long receiver) {
        return sender + "/" + receiver + "/";
    }

    private void saveVideoFile(MultipartFile videoFile, File saveFile) throws IOException {
        // 동영상 파일 저장
        videoFile.transferTo(saveFile);
    }
}
