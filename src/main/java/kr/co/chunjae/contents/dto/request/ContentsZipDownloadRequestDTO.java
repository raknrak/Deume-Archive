package kr.co.chunjae.contents.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ContentsZipDownloadRequestDTO {
    @NotNull(message = "콘텐스 idx는 필수 입력값입니다")
    @Positive(message = "콘텐츠 idx는 양의 정수입니다")
    private Integer contentsIdx;
}
