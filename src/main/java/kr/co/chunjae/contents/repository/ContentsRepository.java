package kr.co.chunjae.contents.repository;

import kr.co.chunjae.contents.dto.ContentsDTO;
import kr.co.chunjae.contents.mapper.ContentsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ContentsRepository {
    private final ContentsMapper contentsMapper;

    public Integer insertContents(ContentsDTO contentsDTO) {
        return contentsMapper.insertContents(contentsDTO);
    }

    public ContentsDTO selectContents(Integer idx) {
        return contentsMapper.selectContents(idx);
    }

    public Integer insertContentsThumbnailList(Map<String, Object> contentsThumbnailInsertMap) {
        return contentsMapper.insertContentsThumbnailList(contentsThumbnailInsertMap);
    }

    public Integer deleteContentsThumbnailByContentsIdx(Integer contentsIdx) {
        return contentsMapper.deleteContentsThumbnailByContentsIdx(contentsIdx);
    }

    public Integer updateContents(ContentsDTO contentsDTO) {
        return contentsMapper.updateContents(contentsDTO);
    }

    public Map<String, String> selectContentsMainImage(Integer contentsIdx) {
        return contentsMapper.selectContentsMainImage(contentsIdx);
    }

    public List<Map<String, String>> selectContentsThumbnail(Integer contentsIdx) {
        return contentsMapper.selectContentsThumbnail(contentsIdx);
    }
}
