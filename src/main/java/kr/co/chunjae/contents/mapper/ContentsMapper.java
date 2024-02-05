package kr.co.chunjae.contents.mapper;

import kr.co.chunjae.contents.dto.ContentsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ContentsMapper {
    Integer insertContents(ContentsDTO contentsDTO);

    ContentsDTO selectContents(Integer idx);

    Integer insertContentsThumbnailList(Map<String, Object> contentsThumbnailInsertMap);

    Integer deleteContentsThumbnailByContentsIdx(Integer contentsIdx);

    Integer updateContents(ContentsDTO contentsDTO);

    Map<String, String> selectContentsMainImage(Integer contentsIdx);

    List<Map<String, String>> selectContentsThumbnail(Integer contentsIdx);
}
