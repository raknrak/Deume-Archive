package kr.co.chunjae.aws.controller;

import kr.co.chunjae.aws.dto.PresignedUploadRequestDTO;
import kr.co.chunjae.aws.dto.PresignedUploadResponseDTO;
import kr.co.chunjae.aws.dto.PresignedUrlViewRequestDTO;
import kr.co.chunjae.aws.dto.PresignedUrlViewResponseDTO;
import kr.co.chunjae.aws.service.PresignedUrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/presigned")
@Controller
public class PresignedUrlController {

    private final PresignedUrlService presignedUrlService;

    @PostMapping(value = "/upload-url")
    @ResponseBody
    public ResponseEntity<PresignedUploadResponseDTO> generatePresignedUrlForUpload(
                                                @Validated @RequestBody PresignedUploadRequestDTO requestDTO) {
        return ResponseEntity
                .ok()
                .body(presignedUrlService.generatePresignedUrlForUpload(requestDTO));
    }

    @PostMapping(value = "/view-url")
    @ResponseBody
    public ResponseEntity<PresignedUrlViewResponseDTO> generatePregisnedUrlForView(
                                                @Validated @RequestBody PresignedUrlViewRequestDTO requestDTO) {
        return ResponseEntity
                .ok()
                .body(presignedUrlService.generatePresignedUrlForView(requestDTO));
    }
}
