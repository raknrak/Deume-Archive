package kr.co.chunjae.packages.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PackagesMainFileDTO {
    @NotBlank(message = "꾸러미 파일 원본 파일명은 필수 입력값입니다")
    private String original;

    @NotBlank(message = "꾸러미 메인 파일 저장 파일명은 필수 입력값입니다")
    private String saved;
}
