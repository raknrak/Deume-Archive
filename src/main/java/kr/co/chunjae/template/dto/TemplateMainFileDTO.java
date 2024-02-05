package kr.co.chunjae.template.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TemplateMainFileDTO {
    @NotBlank(message = "템플릿 파일 원본 파일명은 필수 입력값입니다")
    private String original;

    @NotBlank(message = "템플릿 파일 저장 파일명은 필수 입력값입니다")
    private String saved;
}
