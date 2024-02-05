package kr.co.chunjae.gif.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GifThumbnailDTO {
    private Integer idx;
    private Integer gifIdx;
    private String original;
    private String saved;
    private String uploadPath;
    private String savedPath;
    private Integer orders;
}
