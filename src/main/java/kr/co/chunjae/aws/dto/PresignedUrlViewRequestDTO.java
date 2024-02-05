package kr.co.chunjae.aws.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PresignedUrlViewRequestDTO {
    @NotBlank(message = "경로는 필수 입력값입니다")
    private String path;

    @NotBlank(message = "파일명은 필수 입력값입니다")
    private String fileNameWithUUID;
}
