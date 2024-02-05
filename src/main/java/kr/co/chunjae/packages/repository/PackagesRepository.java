package kr.co.chunjae.packages.repository;

import kr.co.chunjae.packages.dto.PackagesDTO;
import kr.co.chunjae.packages.mapper.PackagesMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PackagesRepository {
    private final PackagesMapper packagesMapper;

    public Integer insertPackages(PackagesDTO packagesDTO) {
        return packagesMapper.insertPackages(packagesDTO);
    }

    public PackagesDTO selectPackages(Integer idx){
        return packagesMapper.selectPackages(idx);
    }

    public Integer insertPackagesThumbnailList(Map<String, Object> packagesThumbnailInsertMap){
        return packagesMapper.insertPackagesThumbnailList(packagesThumbnailInsertMap);
    }
}
