package kr.co.chunjae.packages.service;

import kr.co.chunjae.packages.dto.PackagesDTO;
import kr.co.chunjae.packages.dto.PackagesSaveDTO;
import kr.co.chunjae.packages.repository.PackagesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PackagesService {
    private final PackagesRepository packagesRepository;

    // 꾸러미 정보 저장
    public Integer insertPackages(PackagesDTO packagesDTO){
        Integer insertResult = packagesRepository.insertPackages(packagesDTO);
        return insertResult;
    }

    // 꾸러미 정보 조회
    public PackagesDTO selectPackages(Integer idx){
        return packagesRepository.selectPackages(idx);
    }

    // 꾸러미와 썸네일, 메인 파일을 저장
    public void insertPackagesWithThumbnailAndMainFile(PackagesSaveDTO requestDTO) {
        PackagesDTO newPackagesDTO = PackagesDTO
                .builder()
                .typeIdx(requestDTO.getType())
                .display(requestDTO.getDisplay())
                .name(requestDTO.getName())
                .keyword(requestDTO.getKeyword())
                .explanation(requestDTO.getExplanation())
                .source(requestDTO.getSource())
                .subcateIdx(requestDTO.getSubCategory())
                .original(requestDTO.getPackagesMainFileDTO().getOriginal())
                .saved(requestDTO.getPackagesMainFileDTO().getSaved())
                .build();
        Integer packagesInfoInsertResult = insertPackages(newPackagesDTO);
        if (packagesInfoInsertResult != 1){
            throw new RuntimeException("꾸러미 정보를 저장하는 중 오류 발생");
        }

        Map<String, Object> packagesThumbnailInsertMap = new HashMap<>();
        packagesThumbnailInsertMap.put("packagesIdx", newPackagesDTO.getIdx());
        packagesThumbnailInsertMap.put("thumbnailList", requestDTO.getPackagesThumbnailList());

        Integer thumbnailInsertResult = packagesRepository.insertPackagesThumbnailList(packagesThumbnailInsertMap);
        if (requestDTO.getPackagesThumbnailList().size() != thumbnailInsertResult) {
            throw new RuntimeException("꾸러미 썸네일 정보를 저장하는 중 오류 발생");
        }
    }
}