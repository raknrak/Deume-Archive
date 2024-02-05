package kr.co.chunjae.packages.mapper;

import kr.co.chunjae.packages.dto.PackagesDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface PackagesMapper {
    Integer insertPackages(PackagesDTO packagesDTO);

    PackagesDTO selectPackages(Integer idx);

    Integer insertPackagesThumbnailList(Map<String, Object> packagesThumbnailInsertMap);
}
