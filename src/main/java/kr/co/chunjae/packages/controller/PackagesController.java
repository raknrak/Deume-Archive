package kr.co.chunjae.packages.controller;

import kr.co.chunjae.packages.dto.PackagesSaveDTO;
import kr.co.chunjae.packages.service.PackagesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/packages")
public class PackagesController {
    private final PackagesService packagesService;

    // 뷰를 반환하는 메서드
    @GetMapping(value = "/loadPackages")
    public String packagesLoadForm(){
        return "packages/loadPackages";
    }

    // 패키지를 저장하기 위한 HTTP POST 요청을 처리하는 메서드
    @PostMapping(value = "/loadPackages")
    public ResponseEntity<?> packagesSave(@Validated @RequestBody PackagesSaveDTO requestDTO){
        log.info("requestDTO = {}", requestDTO);

        // 썸네일과 메인 파일을 포함한 패키지 정보를 저장
        packagesService.insertPackagesWithThumbnailAndMainFile(requestDTO);

        // client로 반환할 응답 메시지
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", "꾸러미가 등록되었습니다");

        // CREATE 상태와 응답 메시지를 포함한 ResponseEntity를 반환
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resultMap);
    }
}
