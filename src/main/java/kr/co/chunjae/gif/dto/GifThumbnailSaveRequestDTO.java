package kr.co.chunjae.gif.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class GifThumbnailSaveRequestDTO {
    @NotBlank(message = "콘텐츠 썸네일 원본 파일명은 필수 입력값입니다")
    private String original;

    @NotBlank(message = "콘텐츠 썸네일 저장 파일명은 필수 입력값입니다")
    private String saved;

    @NotNull(message = "콘텐츠 썸네일 순서는 필수 입력값입니다")
    @Positive(message = "콘텐츠 썸네일 순서는 양의 정수입니다")
    private Integer orders;
}
