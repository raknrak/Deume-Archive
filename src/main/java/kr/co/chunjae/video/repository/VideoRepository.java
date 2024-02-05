package kr.co.chunjae.video.repository;

import kr.co.chunjae.video.dto.VideoDTO;
import kr.co.chunjae.video.mapper.VideoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class VideoRepository {
    private final VideoMapper videoMapper;

    public Integer saveVideo(VideoDTO videoDTO) {
        return videoMapper.saveVideo(videoDTO);
    }

   /* public VideoDTO selectVideo(Integer idx) {
        return videoMapper.selectVideo(idx);
    }*/

    public Integer videoThumbnailsList(Map<String, Object> videoThumbnailsSaveMap) {
        return videoMapper.videoThumbnailsList(videoThumbnailsSaveMap);
    }

    public List<VideoDTO> videoList() {
        return videoMapper.videoList();
    }
}
