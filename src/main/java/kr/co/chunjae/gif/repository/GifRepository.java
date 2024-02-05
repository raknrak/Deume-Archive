package kr.co.chunjae.gif.repository;

import kr.co.chunjae.gif.dto.GifDTO;
import kr.co.chunjae.gif.mapper.GifMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class GifRepository {
    private final GifMapper gifMapper;

    public Integer insertGif(GifDTO gifDTO) {
        Integer insertResult = gifMapper.insertGif(gifDTO);
        return insertResult;
    }

    public GifDTO selectGif(Integer idx) {
        return gifMapper.selectGif(idx);
    }

    public Integer insertGifThumbnailList(Map<String, Object> gifThumbnailInsertMap) {
        return gifMapper.insertGifThumbnailList(gifThumbnailInsertMap);
    }

    public Integer deleteGifThumbnailByGifIdx(Integer gifIdx) {
        return gifMapper.deleteGifThumbnailByGifIdx(gifIdx);
    }

    public Integer updateGif(GifDTO gifDTO) {
        return gifMapper.updateGif(gifDTO);
    }

    public Map<String, String> selectGifMainImage(Integer gitIdx) {
        return  gifMapper.selectGifMainImage(gitIdx);
    }

    public List<Map<String, String>> selectGifThumbnail(Integer gitIdx) {
        return  gifMapper.selectGifThumbnail(gitIdx);
    }

    public List<GifDTO> getGifList() {
        return gifMapper.getGifList();
    }
}
