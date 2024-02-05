package kr.co.chunjae.aws.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PresignedUploadResponseDTO {
    private String presignedUrl;
    private String path;
    private String originalFileName;
    private String fileNameWithUUID;
}
