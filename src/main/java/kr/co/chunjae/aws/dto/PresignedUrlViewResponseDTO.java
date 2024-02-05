package kr.co.chunjae.aws.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PresignedUrlViewResponseDTO {
    private String path;
    private String originalFileName;
    private String fileNameWithUUID;
    private String presignedUrl;
}
