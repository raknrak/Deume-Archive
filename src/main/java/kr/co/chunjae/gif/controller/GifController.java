package kr.co.chunjae.gif.controller;


import kr.co.chunjae.gif.dto.GifDTO;
import kr.co.chunjae.gif.dto.GifSaveRequestDTO;
import kr.co.chunjae.gif.service.GifService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/gif")
public class GifController {
    private final GifService gifService;

    @GetMapping(value = "/save")
    public String gifSaveForm() {
        return "gif/save";
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> gifSave(@Validated @RequestBody GifSaveRequestDTO requestDTO) {
        log.info("requestDTO = {}", requestDTO);

        gifService.insertGifWithThumbnailAndMainImage(requestDTO);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", "콘텐츠가 등록되었습니다");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resultMap);
    }

    @GetMapping(value = "/update/{gifIdx}")
    public String updateGif(@PathVariable Integer gifIdx) {
        return "gif/update";
    }

    @GetMapping(value = "/update/get-info/{gifIdx}")
    public ResponseEntity<?> getGifInformation(@PathVariable String gifIdx) {
        Integer gifIdxInt = null;
        Map<String, Object> errorMap = new HashMap<>();

        try {
            gifIdxInt = Integer.parseInt(gifIdx);
        } catch (Exception e) {
            log.error("숫자가 아닌 값이 입력되었습니다");
            errorMap.put("errorMessage", "숫자가 아닌 값이 입력되었습니다");
            return ResponseEntity
                    .badRequest()
                    .body(errorMap);
        }

        GifDTO gifDTO = gifService.selectGif(gifIdxInt);
        log.info("gifDTO = {}", gifDTO);

        if (gifDTO == null) {
            errorMap.put("errorMessage", "해당 콘텐츠 idx를 찾을 수 없습니다");
            return ResponseEntity
                    .badRequest()
                    .body(errorMap);
        }

        return ResponseEntity
                .ok(gifDTO);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<?> gifUpdate(@Validated @RequestBody GifSaveRequestDTO requestDTO) {
        log.info("requestDTO = {}", requestDTO);

        gifService.updateGifWithThumbnailAndMainImage(requestDTO);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", "콘텐츠가 업데이트 되었습니다");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultMap);
    }
    @GetMapping(value = "/detail/{gifIdx}")
    public String detailGif(@PathVariable Integer gifIdx) {
        return "gif/detail";
    }

    @GetMapping(value = "/detail/get-info/{gifIdx}")
    public ResponseEntity<?> detailGifInformation(@PathVariable String gifIdx , Model model) {
        Integer gifIdxInt = null;
        Map<String, Object> errorMap = new HashMap<>();

        try {
            gifIdxInt = Integer.parseInt(gifIdx);
        } catch (Exception e) {
            log.error("숫자가 아닌 값이 입력되었습니다");
            errorMap.put("errorMessage", "숫자가 아닌 값이 입력되었습니다");
            return ResponseEntity
                    .badRequest()
                    .body(errorMap);
        }

        GifDTO gifDTO = gifService.selectGif(gifIdxInt);
        log.info("gifDTO = {}", gifDTO);
        model.addAttribute("gifDTO",gifDTO);
        if (gifDTO == null) {
            errorMap.put("errorMessage", "해당 콘텐츠 idx를 찾을 수 없습니다");
            return ResponseEntity
                    .badRequest()
                    .body(errorMap);
        }

        return ResponseEntity
                .ok(gifDTO);
    }
    @GetMapping(value = "/list")
    public String gifList(Model model) {

        List<GifDTO> gifList = gifService.getGifList();
        log.info(gifList.toString());
        model.addAttribute("gifList", gifList);

        return "gif/list";
    }
}