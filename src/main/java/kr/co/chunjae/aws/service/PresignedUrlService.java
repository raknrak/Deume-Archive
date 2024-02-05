package kr.co.chunjae.aws.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import kr.co.chunjae.aws.dto.PresignedUploadRequestDTO;
import kr.co.chunjae.aws.dto.PresignedUploadResponseDTO;
import kr.co.chunjae.aws.dto.PresignedUrlViewRequestDTO;
import kr.co.chunjae.aws.dto.PresignedUrlViewResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PresignedUrlService {

    @Value(value = "${application.bucket.name}")
    private String bucketName;

    private final AmazonS3 s3Client;

    public PresignedUploadResponseDTO generatePresignedUrlForUpload(PresignedUploadRequestDTO requestDTO) {
        final String  path = requestDTO.getPath();
        final String originalFileName = requestDTO.getOriginalFileName();
        final String originalFileNameWithUUID = concatOriginalFileNameWithUUID(originalFileName);

        return PresignedUploadResponseDTO.builder()
                .presignedUrl(s3Client.generatePresignedUrl(generatePresignedUrlRequest(
                                        bucketName,
                        path + originalFileNameWithUUID,
                                        HttpMethod.PUT,
                            10
                        )).toString())
                .path(path)
                .originalFileName(originalFileName)
                .fileNameWithUUID(originalFileNameWithUUID)
                .build();
    }

    private GeneratePresignedUrlRequest generatePresignedUrlRequest(final String bucketName,
                                                                    final String fileNameWithFullPath,
                                                                    final HttpMethod requiredHttpMethod,
                                                                    final int requiredMinutes) {
        return new GeneratePresignedUrlRequest(bucketName, fileNameWithFullPath)
                .withMethod(requiredHttpMethod)
                .withExpiration(generatePresignedUrlExpirationTime(requiredMinutes));
    }

    private Date generatePresignedUrlExpirationTime(final int minutes) {
        Date expiration = new Date();

        long expirationTimeMilliSec = expiration.getTime();
        expirationTimeMilliSec += 1000 * 60 * minutes;
        expiration.setTime(expirationTimeMilliSec);
        log.info(expiration.toString());

        return expiration;
    }

    private String concatOriginalFileNameWithUUID(final String originalFileName) {
        return UUID.randomUUID() + "_" + originalFileName;
    }

    public PresignedUrlViewResponseDTO generatePresignedUrlForView(PresignedUrlViewRequestDTO requestDTO) {
        final String path = requestDTO.getPath();
        final String fileNameWithUUID = requestDTO.getFileNameWithUUID();

        final String originalFileName = fileNameWithUUID.substring(fileNameWithUUID.indexOf('_') + 1,
                                                                    fileNameWithUUID.length());

        return PresignedUrlViewResponseDTO.builder()
                .path(path)
                .originalFileName(originalFileName)
                .fileNameWithUUID(fileNameWithUUID)
                .presignedUrl(s3Client.generatePresignedUrl(
                        generatePresignedUrlRequest(
                                bucketName,
                path + fileNameWithUUID,
                                HttpMethod.GET,
                    30)
                ).toString())
                .build();
    }
}
