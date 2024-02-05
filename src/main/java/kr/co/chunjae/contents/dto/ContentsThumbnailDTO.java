package kr.co.chunjae.contents.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContentsThumbnailDTO {
    private Integer idx;
    private Integer contentsIdx;
    private String original;
    private String saved;
    private String uploadPath;
    private String savedPath;
    private Integer orders;
}
