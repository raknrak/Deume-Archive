package kr.co.chunjae.gif.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GifMainImageSaveRequestDTO {
    @NotBlank(message = "썸네일 메인 이미지 원본 파일명은 필수 입력값입니다")
    private String original;

    @NotBlank(message = "썸네일 메인 이미지 저장 파일명은 필수 입력값입니다")
    private String saved;
}
