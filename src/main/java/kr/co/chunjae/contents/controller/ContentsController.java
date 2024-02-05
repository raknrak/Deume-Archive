package kr.co.chunjae.contents.controller;

import kr.co.chunjae.contents.dto.ContentsDTO;
import kr.co.chunjae.contents.dto.request.ContentsSaveRequestDTO;
import kr.co.chunjae.contents.dto.request.ContentsZipDownloadRequestDTO;
import kr.co.chunjae.contents.service.ContentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/contents")
public class ContentsController {

    private final ContentsService contentsService;

    @GetMapping(value = "/write")
    public String contentsWriteForm() {
        return "contents/write";
    }

    @PostMapping(value = "/write")
    public ResponseEntity<?> contentsSave(@Validated @RequestBody ContentsSaveRequestDTO requestDTO) {
        log.info("requestDTO = {}", requestDTO);

        contentsService.insertContentsWithThumbnailAndMainImage(requestDTO);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", "콘텐츠가 등록되었습니다");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resultMap);
    }

    @GetMapping(value = "/update/{contentsIdx}")
    public String updateContents(@PathVariable Integer contentsIdx) {
        return "contents/update";
    }

    @GetMapping(value = "/update/get-info/{contentsIdx}")
    public ResponseEntity<?> getContentsInformationForUpdate(@PathVariable String contentsIdx) {
        Integer contentsIdxInt = null;
        Map<String, Object> errorMap = new HashMap<>();

        try {
            contentsIdxInt = Integer.parseInt(contentsIdx);
        } catch (Exception e) {
            log.error("숫자가 아닌 값이 입력되었습니다");
            errorMap.put("errorMessage", "숫자가 아닌 값이 입력되었습니다");
            return ResponseEntity
                    .badRequest()
                    .body(errorMap);
        }

        ContentsDTO contentsDTO = contentsService.selectContents(contentsIdxInt);
        log.info("contentsDTO = {}", contentsDTO);

        if (contentsDTO == null) {
            errorMap.put("errorMessage", "해당 콘텐츠 idx를 찾을 수 없습니다");
            return ResponseEntity
                    .badRequest()
                    .body(errorMap);
        }

        return ResponseEntity
                .ok(contentsDTO);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<?> contentsUpdate(@Validated @RequestBody ContentsSaveRequestDTO requestDTO) {
        log.info("requestDTO = {}", requestDTO);

        contentsService.updateContentsWithThumbnailAndMainImage(requestDTO);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", "콘텐츠가 업데이트 되었습니다");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultMap);
    }

    @GetMapping(value = "/detail/{contentsIdx}")
    public String contentsDetail(@PathVariable String contentsIdx) {
        return "contents/detail";
    }

    @GetMapping(value = "/detail/get-info/{contentsIdx}")
    public ResponseEntity<?> getContentsInformationForDetail(@PathVariable String contentsIdx) {
        Integer contentsIdxInt = null;
        Map<String, Object> errorMap = new HashMap<>();

        try {
            contentsIdxInt = Integer.parseInt(contentsIdx);
        } catch (Exception e) {
            log.error("숫자가 아닌 값이 입력되었습니다");
            errorMap.put("errorMessage", "숫자가 아닌 값이 입력되었습니다");
            return ResponseEntity
                    .badRequest()
                    .body(errorMap);
        }

        ContentsDTO contentsDTO = contentsService.selectContents(contentsIdxInt);
        log.info("contentsDTO = {}", contentsDTO);

        if (contentsDTO == null) {
            errorMap.put("errorMessage", "해당 콘텐츠 idx를 찾을 수 없습니다");
            return ResponseEntity
                    .badRequest()
                    .body(errorMap);
        }

        return ResponseEntity
                .ok(contentsDTO);
    }

    @PostMapping(value = "/zip")
    public ResponseEntity<?> downloadZip(@Validated @RequestBody ContentsZipDownloadRequestDTO requestDTO)
                                                                                            throws IOException {
        Integer contentsIdx = requestDTO.getContentsIdx();
        Map<String, Object> resultMap = null;
        try {
            resultMap = contentsService.downloadZipFile(contentsIdx);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ByteArrayResource byteArrayResource = (ByteArrayResource) resultMap.get("zipFile");
        String zipFileName = (String) resultMap.get("zipFileName");
        return ResponseEntity
                .ok()
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition",
                        "attachment;filename=" +
                                new String(zipFileName.getBytes("utf-8"), "ISO-8859-1"))
                .body(byteArrayResource);
    }
}
