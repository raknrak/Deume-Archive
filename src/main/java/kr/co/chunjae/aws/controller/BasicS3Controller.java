package kr.co.chunjae.aws.controller;

import kr.co.chunjae.aws.dto.BasicS3DownloadRequestDTO;
import kr.co.chunjae.aws.service.BasicS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/basic")
public class BasicS3Controller {

    private final BasicS3Service service;

    @PostMapping(value = "/download")
    @ResponseBody
    public ResponseEntity<?> downloadFile(@RequestBody BasicS3DownloadRequestDTO requestDTO)
                                                                throws UnsupportedEncodingException {
        log.info("requestDTO = {}", requestDTO);

        ByteArrayResource byteArrayResource = service.downloadFile(requestDTO);

        return ResponseEntity
                .ok()
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition",
                        "attachment;filename=" +
                                new String(requestDTO
                                        .getFileNameWithUUID().getBytes("utf-8"), "ISO-8859-1"))
                .body(byteArrayResource);
    }

    @GetMapping(value = "/download")
    @ResponseBody
    public ResponseEntity<?> gdownloadFile(@RequestParam String path, @RequestParam String fileNameWithUUID)
            throws UnsupportedEncodingException {
        BasicS3DownloadRequestDTO requestDTO = BasicS3DownloadRequestDTO.builder().path(path).fileNameWithUUID(fileNameWithUUID).build();
        log.info("requestDTO = {}", requestDTO);

        ByteArrayResource byteArrayResource = service.downloadFile(requestDTO);

        return ResponseEntity
                .ok()
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition",
                        "attachment;filename=" +
                                new String(requestDTO
                                        .getFileNameWithUUID().getBytes("utf-8"), "ISO-8859-1"))
                .body(byteArrayResource);
    }
}
