package kr.co.chunjae.contents.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ContentsMainImageSaveRequestDTO {
    @NotBlank(message = "썸네일 메인 이미지 원본 파일명은 필수 입력값입니다")
    private String original;

    @NotBlank(message = "썸네일 메인 이미지 저장 파일명은 필수 입력값입니다")
    private String saved;
}
