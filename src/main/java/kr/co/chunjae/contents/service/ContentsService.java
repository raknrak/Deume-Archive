package kr.co.chunjae.contents.service;

import kr.co.chunjae.aws.dto.BasicS3DownloadRequestDTO;
import kr.co.chunjae.aws.service.BasicS3Service;
import kr.co.chunjae.contents.dto.ContentsDTO;
import kr.co.chunjae.contents.dto.request.ContentsSaveRequestDTO;
import kr.co.chunjae.contents.repository.ContentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ContentsService {

    private final ContentsRepository contentsRepository;
    private final BasicS3Service basicS3Service;

    public Integer insertContents(ContentsDTO contentsDTO) {
        Integer insertResult = contentsRepository.insertContents(contentsDTO);
        return insertResult;
    }

    public ContentsDTO selectContents(Integer idx) {
        return contentsRepository.selectContents(idx);
    }

    public void insertContentsWithThumbnailAndMainImage(ContentsSaveRequestDTO requestDTO) {
        ContentsDTO newContentsDTO = ContentsDTO
                .builder()
                .typeIdx(requestDTO.getType())
                .display(requestDTO.getDisplay())
                .name(requestDTO.getName())
                .keyword(requestDTO.getKeyword())
                .explanation(requestDTO.getExplanation())
                .source(requestDTO.getSource())
                .subcateIdx(requestDTO.getSubCategory())
                .original(requestDTO.getMainImageSaveRequestDTO().getOriginal())
                .saved(requestDTO.getMainImageSaveRequestDTO().getSaved())
                .build();
        Integer contentsInfoInsertResult = insertContents(newContentsDTO);
        if (contentsInfoInsertResult != 1) {
            throw new RuntimeException("콘텐츠 정보를 저장하는 중 오류 발생");
        }

        Map<String, Object> contentsThumbnailInsertMap = new HashMap<>();
        contentsThumbnailInsertMap.put("contentsIdx", newContentsDTO.getIdx());
        contentsThumbnailInsertMap.put("thumbnailList", requestDTO.getThumbnailSaveRequestList());

        Integer thumbnailInsertResult = contentsRepository.insertContentsThumbnailList(contentsThumbnailInsertMap);
        if (requestDTO.getThumbnailSaveRequestList().size() != thumbnailInsertResult) {
            throw new RuntimeException("콘텐츠 썸네일 정보를 저장하는 중 오류 발생");
        }
    }

    public void updateContentsWithThumbnailAndMainImage(ContentsSaveRequestDTO requestDTO) {
        // 원래 있던 contentsThumbnail 삭제
        Integer contentsThumbnailDeleteResult =
                contentsRepository.deleteContentsThumbnailByContentsIdx(requestDTO.getIdx());

        ContentsDTO contentsDTOForUpdate = ContentsDTO
                .builder()
                .idx(requestDTO.getIdx())
                .typeIdx(requestDTO.getType())
                .display(requestDTO.getDisplay())
                .name(requestDTO.getName())
                .keyword(requestDTO.getKeyword())
                .explanation(requestDTO.getExplanation())
                .source(requestDTO.getSource())
                .subcateIdx(requestDTO.getSubCategory())
                .original(requestDTO.getMainImageSaveRequestDTO().getOriginal())
                .saved(requestDTO.getMainImageSaveRequestDTO().getSaved())
                .build();

        Integer contentsUpdateResult = contentsRepository.updateContents(contentsDTOForUpdate);

        Map<String, Object> contentsThumbnailInsertMap = new HashMap<>();
        contentsThumbnailInsertMap.put("contentsIdx", contentsDTOForUpdate.getIdx());
        contentsThumbnailInsertMap.put("thumbnailList", requestDTO.getThumbnailSaveRequestList());

        Integer contentsThumbnailInsertResult = contentsRepository.insertContentsThumbnailList(contentsThumbnailInsertMap);
    }

    public Map<String, Object> downloadZipFile(Integer contentsIdx) throws IOException {
        List<String> filePathList = new ArrayList<>();
        List<String> fileNameList = new ArrayList<>();

        // contents main image 경로 및 파일명uuid 를 조회
        Map<String, String> mainFilePathAndFileName = contentsRepository.selectContentsMainImage(contentsIdx);
        filePathList.add(mainFilePathAndFileName.get("uploadPath"));
        fileNameList.add(mainFilePathAndFileName.get("saved"));

        // 특정 contents에 연관된 thumbnail 파일들을 조회
        List<Map<String, String>> thumbnailInformationList = contentsRepository.selectContentsThumbnail(contentsIdx);

        thumbnailInformationList.forEach((thumbnail) -> {
            filePathList.add(thumbnail.get("uploadPath"));
            fileNameList.add(thumbnail.get("saved"));
        });

        List<ByteArrayResource> byteArrayResourceList = new ArrayList<>();

        for (int i = 0; i < filePathList.size(); i++) {
            ByteArrayResource byteArrayResource = basicS3Service.downloadFile(
                    BasicS3DownloadRequestDTO.builder()
                            .path(filePathList.get(i))
                            .fileNameWithUUID(fileNameList.get(i))
                            .build());
            byteArrayResourceList.add(byteArrayResource);
        }

        List<File> fileList = new ArrayList<>();

        for (int i = 0; i < byteArrayResourceList.size(); i++) {
            ByteArrayResource resource = byteArrayResourceList.get(i);
            InputStream inputStream = resource.getInputStream();
            String fileName = fileNameList.get(i);
            String fileNameWithoutUUIDAndExtension = fileName.substring(
                                                        fileName.indexOf("_") + 1,
                                                        fileName.lastIndexOf("."));
            String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            File tempFile = File.createTempFile(fileNameWithoutUUIDAndExtension, extension);
            FileCopyUtils.copy(inputStream.readAllBytes(), tempFile);
            fileList.add(tempFile);
        }

        LocalDateTime nowDate = LocalDateTime.now();
        String formatDate = nowDate.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String zipFileName = "DeumeArchive-contents_" + formatDate;
        File zipFile = File.createTempFile(zipFileName, ".zip");
        zipFile.deleteOnExit();

        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile))) {
            byte[] buf = new byte[1_024];
            for (File file : fileList) {
                if (file.exists()) {
                    try (FileInputStream fis = new FileInputStream(file)) {
                        ZipEntry zipEntry = new ZipEntry(file.getName());
                        out.putNextEntry(zipEntry);
                        int len;
                        while ((len = fis.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        out.closeEntry();
                    }
                }
            }
            for (File file : fileList) {
                deleteFile(file);
            }
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("zipFileName", zipFileName + ".zip");
        resultMap.put("zipFile", new ByteArrayResource(FileCopyUtils.copyToByteArray(zipFile)));
        return resultMap;
    }

    private void deleteFile(File file) {
        if (file.exists()) {
            file.delete();
        }
    }
}
