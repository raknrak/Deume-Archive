package kr.co.chunjae.video.service;

import kr.co.chunjae.video.dto.VideoDTO;
import kr.co.chunjae.video.dto.VideoSaveRequestDTO;
import kr.co.chunjae.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class VideoService {
    private final VideoRepository videoRepository;

    public Integer saveVideo(VideoDTO videoDTO) {
        Integer saveResult = videoRepository.saveVideo(videoDTO);
        return saveResult;
    }

    /*public VideoDTO selectVideo(Integer idx) {
        return videoRepository.selectVideo(idx);
    }*/
    public void saveVideoWithThumbnailAndVideoFile(VideoSaveRequestDTO saveRequestDTO) {
        VideoDTO newVideoDTO = VideoDTO
                .builder()
                .typeIdx(saveRequestDTO.getType())
                .display(saveRequestDTO.getDisplay())
                .subcateIdx(saveRequestDTO.getSubCategory())
                .name(saveRequestDTO.getName())
                .explanation(saveRequestDTO.getExplanation())
                .source(saveRequestDTO.getSource())
                .keyword(saveRequestDTO.getKeyword())
                .original(saveRequestDTO.getFileSaveRequestDTO().getOriginal())
                .saved(saveRequestDTO.getFileSaveRequestDTO().getSaved())
                .build();
        log.info("saveRequestDTO = ", saveRequestDTO);
        Integer videoInfoSaveResult = saveVideo(newVideoDTO);
        if (videoInfoSaveResult != 1) {
            throw new RuntimeException("upload video Error!");
        }
        Map<String, Object> videoThumbnailsSaveMap = new HashMap<>();
        videoThumbnailsSaveMap.put("videoIdx", newVideoDTO.getIdx());
        videoThumbnailsSaveMap.put("thumbnailList", saveRequestDTO.getThumbnailsSaveRequestList());

        Integer videoThumbnailsSaveResult = videoRepository.videoThumbnailsList(videoThumbnailsSaveMap);
        if (saveRequestDTO.getThumbnailsSaveRequestList().size() != videoThumbnailsSaveResult) {
        throw new RuntimeException("thumbnail(s) saved Error");
        }
    }

    public List<VideoDTO> videoList() {
        return videoRepository.videoList();
    }
}
