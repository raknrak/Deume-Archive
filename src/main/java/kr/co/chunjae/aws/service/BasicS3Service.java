package kr.co.chunjae.aws.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import kr.co.chunjae.aws.dto.BasicS3DownloadRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class BasicS3Service {
    @Value(value = "${application.bucket.name}")
    private String bucketName;

    private final AmazonS3 s3Client;

    public ByteArrayResource downloadFile(BasicS3DownloadRequestDTO requestDTO) {
        final String path = requestDTO.getPath();
        final String fileNameWithUUID = requestDTO.getFileNameWithUUID();

        S3Object s3Object = s3Client.getObject(bucketName, path + fileNameWithUUID);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] byteArray = IOUtils.toByteArray(inputStream);
            return new ByteArrayResource(byteArray);
        } catch (IOException e) {
            log.error("downloadFile error: ", e);
        }
        return null;
    }
}
