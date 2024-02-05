package kr.co.chunjae.video.controller;

import kr.co.chunjae.video.dto.VideoDTO;
import kr.co.chunjae.video.dto.VideoSaveRequestDTO;
import kr.co.chunjae.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/video")
public class VideoController {
    private final VideoService videoService;

    @GetMapping("/save")
    public String videoSaveForm(){
        return "video/save";
    }

    @PostMapping("/save")
    public ResponseEntity<?> videoSave(@RequestBody VideoSaveRequestDTO saveRequestDTO){
        log.info("saveRequestDTO ={}", saveRequestDTO);
        videoService.saveVideoWithThumbnailAndVideoFile(saveRequestDTO);
        log.info("saveRequestDTO ={}", saveRequestDTO);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", "등록완료");

        return ResponseEntity
                .status(HttpStatus.CREATED) //Post에서는 201(created)를 반환
                .body(resultMap);
    }

    @GetMapping("/list")
    public String videoList(Model model) {
        List<VideoDTO> videoDTOList = videoService.videoList();
        model.addAttribute("videoList",videoDTOList);
        return "video/videoList";
    }

    /*@GetMapping("/view")
    public String findByVideoIdx(@RequestParam Integer idx, Model model) {

        VideoDTO videoDTO = videoService.findByVideoIdx(idx);
        model.addAttribute("idx", idx);
        model.addAttribute("video", videoDTO);
        return "video/videoView"

    }*/
}
