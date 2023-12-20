package com.kbt1.ollilove.transferservice.service;

import com.kbt1.ollilove.transferservice.dto.VideoRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class VideoServiceImpl implements VideoService{
    @Value("${upload.dir}")
    private String uploadDir;

    public String saveVideo(VideoRequestDTO videoRequestDTO) {
        // 고유한 주소값 생성
        String uniqueAddress = generateUniqueAddress(videoRequestDTO.getSenderId(), videoRequestDTO.getReceiverId());
        // 저장될 파일 경로
        String filePath = uploadDir + File.separator + uniqueAddress;

        //파일명 제작

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
        //현재 시각을 파일이름으로 설정
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");

        //확장자명 추출
        String originName = videoRequestDTO.getVideo().getOriginalFilename();
        String fileType = getFileExtension(originName);
        String fileName = dateFormat.format(currentDate) + "." + fileType;

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

        return filePath+fileName; // 또는 DB에 저장할 경우 저장된 주소를 반환
    }

    private String generateUniqueAddress(Long sender, Long receiver) {
        return sender + "/" + receiver + "/";
    }

    private void saveVideoFile(MultipartFile videoFile, File saveFile) throws IOException {
        // 동영상 파일 저장
        videoFile.transferTo(saveFile);
    }
    //파일 확장자 추출
    private static String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return filename.substring(lastDotIndex + 1);
        }
        return ""; // 확장자가 없는 경우 빈 문자열 반환
    }
}
