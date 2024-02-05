package kr.co.chunjae.aws.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicS3DownloadRequestDTO {
    @NotBlank(message = "파일 경로는 필수 입력값입니다")
    private String path;

    @NotBlank(message = "UUID가 포함된 파일명은 필수 입력값입니다")
    private String fileNameWithUUID;
}
