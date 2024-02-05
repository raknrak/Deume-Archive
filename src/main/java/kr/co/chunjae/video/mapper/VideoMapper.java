package kr.co.chunjae.video.mapper;

import kr.co.chunjae.video.dto.VideoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface VideoMapper {
    Integer saveVideo(VideoDTO videoDTO);

/*    VideoDTO selectVideo(Integer idx);*/

    Integer videoThumbnailsList(Map<String, Object> videoThumbnailsSaveMap);

    List<VideoDTO> videoList();
}
